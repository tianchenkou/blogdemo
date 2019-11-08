package me.koutian.controller;

import com.alibaba.fastjson.JSONObject;
import me.koutian.annotation.UserLoginToken;
import me.koutian.bean.Category;
import me.koutian.bean.CategoryDetails;
import me.koutian.mapper.CateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author: KouTian
 * @date: 2019-10-22 19:35
 * @description
 */
@RestController
public class CateController {

    @Autowired
    CateMapper cateMapper;
    Logger logger = LoggerFactory.getLogger(getClass());

    //添加允许跨域请求的  ip  和  预检有效期
//    @CrossOrigin(origins = "http://localhost:8092", maxAge = 3600)
    @UserLoginToken
    @GetMapping("/getAllCate")
    public Map<String, Object> getAllCategory() {
        HashMap<String, Object> map = null;
        List<Category> categories = null;

        //交给前台验证的状态码
        int code = 200;
        String msg = "查询成功";

        //异常处理
        try {
            categories = cateMapper.findAllCate();

            //判断链表是否为空
            if (categories == null || categories.size() == 0) {
                msg = "博客分类信息为空";
                logger.warn("博客分类信息为空");
            }

        } catch (Exception e) {
            code = 500;
            msg = "查询失败，发生异常";
            logger.warn("查询失败，发生异常");
        }

        map = new HashMap<>();

        map.put("code", code);
        map.put("msg", msg);
        map.put("info", categories);


        return map;
    }
    @UserLoginToken
    @PostMapping("/setCate")
    public Map<String, Object> setCate(@RequestBody JSONObject json) {
        Map<String, Object> map = null;
        //交给前台验证的状态码
        int code = 200;
        String msg = "查询成功";

        String cate = null;
        String deCate = null;
        String isNew = null;
        try {
            cate = json.getString("topCate").toString();
            isNew = json.getString("isNew").toString();
            deCate = json.getString("subCate").toString();
        } catch (NullPointerException e) {
            map = new HashMap<>();
            code = 503;
            msg = "操作失败，必选项为空";
            logger.warn("保存失败，必选项为空");
            map.put("code", code);
            map.put("msg", msg);

            return map;
        }


        //参数处理
        if (isNew.equals("true")) {//该次请求为新建一级目录
            Category category = new Category();
            CategoryDetails categoryDetails1 = new CategoryDetails();
            //这里前台做了保证，这里就不去数据库验证了

            category.setName(cate);
            categoryDetails1.setName(deCate);
            categoryDetails1.setParent(category);

            ArrayList<CategoryDetails> list = new ArrayList<>();

            list.add(categoryDetails1);
            category.setSonCate(list);

            try {
                cateMapper.insertCate(category);
                for (CategoryDetails categoryDetails : list
                ) {
                    cateMapper.insertCategoryDetails(categoryDetails);
                }
            } catch (Exception e) {
                code = 500;
                msg = "操作失败，后台异常";
                logger.error("操作失败，后台异常");
            }
        } else {//不是新建的一级目录
            String cateId = json.getString("subid");
            if (cateId == null) {
                code = 503;
                msg = "操作失败，必选项为空";
                logger.warn("保存失败，必选项为空");
            } else {
                try {
                    CategoryDetails categoryDetails = new CategoryDetails();
                    categoryDetails.setParentId(cateId);
                    categoryDetails.setName(deCate);

                    cateMapper.insertCategoryDetail(categoryDetails);

                } catch (Exception e) {
                    code = 500;
                    msg = "操作失败，后台异常";
                    logger.error("操作失败，后台异常");
                }
            }
        }
        map = new HashMap<>();

        map.put("code", code);
        map.put("msg", msg);
        return map;
    }
    @UserLoginToken
    @PostMapping("/uploadImg")
    public Map<String, Object> doSaveImg(@RequestBody JSONObject json) {
        System.out.println(json);
        String base64Data = json.getString("base64Data");

        logger.info("==上传图片==");
        logger.info("==接收到的数据==" + base64Data);


        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据
        if (base64Data == null || "".equals(base64Data)) {
            return AppResultBuilder.buildFailedResult("上传失败，上传图片数据为空", "401");
        } else {
            String[] d = base64Data.split("base64,");//将字符串分成数组
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                return AppResultBuilder.buildFailedResult("上传失败，数据不合法", "401");
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        } else {
            return AppResultBuilder.buildFailedResult("上传图片格式不合法", "401");
        }

        //生成随机码
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName = uuid + suffix;
        String imgFilePath = "D:\\code\\vscode\\vue\\vue-cli-study\\blog-demo\\src\\assets\\koutianchen\\" + tempFileName;//新生成的图片的路径
        //String imgFilePath = "/data/nginx/html/static/img/koutianchen"+tempFileName;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            //返回给前端图片的url地址
            String imgurl = "/koutianchen/" + tempFileName;


            //imageService.save(imgurl);
            return AppResultBuilder.buildSuccessResult("上传成功", "200", imgurl);
        } catch (IOException e) {
            e.printStackTrace();
            return AppResultBuilder.buildFailedResult("上传图片失败", "401");
        }
    }

}
