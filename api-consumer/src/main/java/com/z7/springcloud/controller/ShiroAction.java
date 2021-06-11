//package com.z7.springcloud.controller;
//
//import com.aegis.legal.model.beans.common.Msg;
//import com.aegis.legal.model.beans.common.ResultData;
//import com.aegis.legal.model.beans.jpa.User;
//import com.aegis.legal.service.inter.UserService;
//import com.aegis.legal.utils.Md5Utils;
//import com.aegis.legal.utils.RedisUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import sun.misc.BASE64Encoder;
//
//import javax.validation.constraints.NotBlank;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author z7
// */
//@Validated
//@RestController
//@Api(tags = "通用-身份验证、授权")
//@RequestMapping(value = "api/shiro")
//public class ShiroAction {
//
//  @Autowired
//  private UserService userService;
//
//  @PostMapping("login")
//  @ApiOperation(value = "登陆", notes = "返回成功或失败信息")
//  public ResultData login(
//      @NotBlank(message = "缺失用户名")
//      @ApiParam(value = "用户名", required = true, defaultValue = "admin")
//      @RequestParam String username,
//      @NotBlank(message = "缺失AES加密过的密码")
//      @ApiParam(value = "AES加密过的密码", required = true, defaultValue = "EzXolZTjon318pVVhKAJfw==")
//      @RequestParam String encPassword,
//      @NotBlank(message = "缺失key")
//      @ApiParam(value = "key", required = true, defaultValue = "1617672881846fxy")
//      @RequestParam String key
//  ) {
//
//    //用于校验是否是第三方平台账号直接登陆本系统
//    User user = userService.findUserByAccount(username);
//    if (user == null || "统管平台".equals(user.getOrigin())){
//      return new ResultData(Msg.PARAM,"账号不存在或未启用");
//    }
//
//    //3分钟之内登陆失败超过5次，账号锁定24小时 modify by chenchao 2020-01-12 第三方检测机构整改新增需求
//    boolean exit = RedisUtil.hasKey(username);
//    if (exit && ("lock".equals(RedisUtil.get(username)))) {
//      return new ResultData<>(null, "登陆失败5次，账号已锁定，请24小时后尝试", 17);
//    } else if (exit && 5 == (Integer) RedisUtil.get(username)) {
//        //锁帐号
//        RedisUtil.set(username, "lock");
//        //设置过期时间为24小时(账号锁定24小时，24小时之后解除锁定)
//        RedisUtil.expire(username, 24 * 60 * 60);
//      return new ResultData<>(null, "登陆失败5次，账号已锁定，请24小时后尝试", 17);
//    }
//
//    //byte[] encPassword = Md5Utils.encrypt(password,key);//加密(本地测试)
//    String decPassword = Md5Utils.deCode(encPassword,key);
//    if (null == decPassword){
//      return new ResultData(Msg.ERROR,"解码异常");
//    }
//
//    BASE64Encoder base64Encoder = new BASE64Encoder();
//    String encodePwd = base64Encoder.encode(decPassword.getBytes());
//
//    Subject subject = SecurityUtils.getSubject();
//    UsernamePasswordToken token = new UsernamePasswordToken(username, encodePwd);
//    try {
//      subject.login(token);
//      //shiro session 默认30分钟超时 modify by chenchao 2020-01-12 第三方检测机构新增需求
//      //SecurityUtils.getSubject().getSession().setTimeout(1000 * 3600 * 24);
//      if (RedisUtil.hasKey(username)){
//        RedisUtil.delete(username);
//      }
//      //若是初始密码，需强制修改 add by chenchao 2021-01-25
//      if (decPassword.equals("Abc123456")){
//        return new ResultData<>(Msg.UPDATE_PASSWORD);
//      }
//    } catch (AuthenticationException e) {
//      token.clear();
//      if (e instanceof IncorrectCredentialsException) {
//        if (RedisUtil.hasKey(username)){
//          if (!RedisUtil.get(username).equals("true")){
//            RedisUtil.incr(username);
//          }
//        } else {
//          RedisUtil.set(username,1,180);
//        }
//        return new ResultData(Msg.PARAM, "密码错误");
//      }
//      if (RedisUtil.hasKey(username)){
//        if (!RedisUtil.get(username).equals("true")){
//          RedisUtil.incr(username);
//        }
//      } else {
//        RedisUtil.set(username,1,180);
//      }
//      return new ResultData(Msg.PARAM, "账号不存在或未启用");
//    }
//    return new ResultData<>((User)SecurityUtils.getSubject().getPrincipal());
//  }
//
//  /**
//   * 描述：第三方(统一管理平台)单点登陆
//   * 创建人: chenchao
//   * 创建时间：2020-04-17
//   */
//  @PostMapping("singleLogin")
//  public ResultData singleLogin(
//      @NotBlank(message = "缺失token")
//      @RequestParam String tokenUnified,
//      @NotBlank(message = "缺失用户名")
//      @RequestParam String userId
//  ) {
//    //校验token
//    String tokenLeg = "";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    try {
//      tokenLeg = Md5Utils.getMd5(sdf.format(new Date()) + userId);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    if (!tokenLeg.equals(tokenUnified)){
//      return new ResultData(Msg.AUTH_ERROR,"该账号不是从统管平台方登陆");
//    }
//
//    //校验userId
//    User user = userService.findUserByAccount(userId);
//    if (user == null){
//      return new ResultData(Msg.PARAM,"账号不存在");//不存在分两种情况(1：本身不存在，2：is_delete=1)
//    }
//
//    //认证
//    Subject subject = SecurityUtils.getSubject();
//    UsernamePasswordToken token = new UsernamePasswordToken(userId,user.getPassword());
//    try {
//      subject.login(token);
//      SecurityUtils.getSubject().getSession().setTimeout(1000*3600*24);
//    } catch (AuthenticationException e) {
//      token.clear();
//      if (e instanceof IncorrectCredentialsException) {
//        return new ResultData(Msg.PARAM, "密码错误");
//      }
//      return new ResultData(Msg.PARAM, "账号不存在或未启用");
//    }
//    return new ResultData<>((User)SecurityUtils.getSubject().getPrincipal());
//  }
//
//  @ApiOperation("退出登录")
//  @PostMapping("logout")
//  public ResultData logout() {
//    Subject subject = SecurityUtils.getSubject();
//    subject.logout();
//    return new ResultData(Msg.OK);
//  }
//
//}
