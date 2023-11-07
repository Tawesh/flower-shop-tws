package com.tws.flowershop.admin.controler.api;

import com.tws.flowershop.admin.entity.ProductEdit;
import com.tws.flowershop.comment.utils.UpLoadUtil;
import com.tws.flowershop.user.entity.FlowerEntity;
import com.tws.flowershop.user.service.FlowerService;
import com.tws.flowershop.user.service.TokenService;
import com.tws.flowershop.user.service.impl.FlowerImpl;
import jakarta.annotation.Resource;
import jakarta.websocket.OnClose;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TaoWeiShu
 * @date: 2023/10/15  21:04
 */

@RestController
@RequestMapping("/web/tws/fs/api/admin/data")
public class ProductManageApi {

    @Resource
    private FlowerService flowerService;

    @GetMapping("/productInfo")
    public Object findAll() {

        HashMap<String, Object> hashMap = new HashMap<>();
        List<FlowerEntity> findAll = flowerService.adminFindAll();
        hashMap.put("data", 0);
        hashMap.put("response", findAll);
        return hashMap;
    }


    @Resource
    private TokenService tokenService;

    @PostMapping("/product/search")
    public Object selectById(@RequestParam("tk") String tk, @RequestParam("title") String title, @RequestParam("label") String label) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                List<FlowerEntity> flowerEntities = flowerService.adminSearchProduct(title, label);
                hashMap.put("code", 0);
                hashMap.put("response", flowerEntities);
            } else {
                hashMap.put("code", 0);
                hashMap.put("response", "登录已失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常");
        }
        return hashMap;
    }

    @Resource
    private UpLoadUtil upLoadUtil;

    @PostMapping("/product/edit")
    public Object edit(@RequestBody Map<String, String> map,
                       @RequestParam("tk") String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();
        System.out.println("管理员get edit data is" + map);
        try {
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                String id = map.get("id");
                String title = map.get("title");
                String introduce = map.get("introduce");
                float price = Integer.parseInt(map.get("price"));
                String itemLabel = map.get("itemLabel");
                ProductEdit flowerEntity = new ProductEdit();
                flowerEntity.setId(id);
                flowerEntity.setTitle(title);
                flowerEntity.setIntroduce(introduce);
                flowerEntity.setItemLabel(Integer.parseInt(itemLabel));
                flowerEntity.setPrice(price);
                boolean b = flowerService.adminEditProduct(flowerEntity);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "修改成功");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "修改失败");
                }
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录失效");
            }

        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常");
        }
        return hashMap;
    }


    /**
     * 删除
     *
     * @param
     * @return
     */

    @PostMapping("/product/delete")
    public Object delete(@RequestParam("id") String id, @RequestParam("tk") String tk) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {

            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key) {
                boolean b = flowerService.adminDeleteProduct(id);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "删除成功");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "删除失败");
                }
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录已失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "系统异常");
        }

        return hashMap;
    }

    /**
     * 添加
     *
     * @param
     * @return
     */

    @PostMapping("/product/add")
    public Object addUser(@RequestParam("tk") String tk,
                          @RequestBody Map<String, String> map
    ) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String s = tk.substring(7, tk.length() - 7);
        Map<String, String> token = tokenService.findToken(s);
        boolean key = tokenService.analysisToken(token.get("key"), s);
        try {
            if (key) {
                String id = map.get("id");
                String title = map.get("title");
                String introduce = map.get("introduce");
                float price = Integer.parseInt(map.get("price"));
                String itemLabel = map.get("itemLabel");
                String imgUrl = map.get("imgUrl");
                FlowerEntity flowerEntity = new FlowerEntity();
                flowerEntity.setId(id);
                flowerEntity.setTitle(title);
                flowerEntity.setIntroduce(introduce);
                flowerEntity.setItemLabel(Integer.parseInt(itemLabel));
                flowerEntity.setPrice(price);
                flowerEntity.setImgUrl(imgUrl);
                boolean b = flowerService.adminAddProduct(flowerEntity);
                if (b) {
                    hashMap.put("code", 0);
                    hashMap.put("response", "添加成功");
                } else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "添加失败");
                }

            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "服务器检测到恶意请求，已终止访问");
            }
        } catch (NullPointerException nullPointerException) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务器检测到恶意请求，已终止访问");
        }

        return hashMap;
    }

    /**
     * 按ID查询编辑的
     *
     * @param
     * @return
     */
    @PostMapping("/product/byId")
    public Object seById(@RequestParam("tk") String tk, @RequestParam("id") String id) {

        HashMap<String, Object> hashMap = new HashMap<>();
        String s = tk.substring(7, tk.length() - 7);
        Map<String, String> token = tokenService.findToken(s);
        boolean key = tokenService.analysisToken(token.get("key"), s);
        try {

            if (key) {
                FlowerImpl flowerInfoById = flowerService.getFlowerInfoById(id);
                hashMap.put("code", 0);
                hashMap.put("response", flowerInfoById);
            } else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录已失效");
            }

        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常");
        }
        return hashMap;
    }


    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @PostMapping("/product/deleteList")
    public Object deleteList(@RequestParam("tk") String tk, @RequestBody List<String> idList) {
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            String s = tk.substring(7, tk.length() - 7);
            Map<String, String> token = tokenService.findToken(s);
            boolean key = tokenService.analysisToken(token.get("key"), s);
            if (key){
                boolean b = flowerService.adminDeleteByIdList(idList);
                if (b){
                    hashMap.put("code", 0);
                    hashMap.put("response", "删除成功");
                }else {
                    hashMap.put("code", -1);
                    hashMap.put("response", "删除失败");
                }
            }else {
                hashMap.put("code", -1);
                hashMap.put("response", "登录已失效");
            }
        } catch (Exception e) {
            hashMap.put("code", -1);
            hashMap.put("response", "服务异常，请稍后再试");
        }
        return hashMap;
    }
}
