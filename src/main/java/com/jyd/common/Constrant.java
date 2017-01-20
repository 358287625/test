package com.jyd.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.jyd.beans.UserInfor;

public class Constrant {
	
public static final String PERMISSION_ADMIN="35e2e5dbb41a0846e12c80bd138f28af";//_jyd_0@ 的md5值
public static final String PERMISSION_PRINTER_ORDER="5f096c3952a7ef54041c5ac33fdf56b0";//_jyd_1@ 的md5值 打印单据
public static final String PERMISSION_ADD_RECEIVED_ORDER="c85b5056068c1f39a089f718fec077ba";//_jyd_2@ 的md5值 开接件单
public static final String PERMISSION_EDIT_RECEIVED_ORDER="f7ae5b27126b3a1e4302b73b87ea6400";//_jyd_3@ 的md5值 修改接件单
public static final String PERMISSION_REWARD="26b68a76e59b57d0d6370eaf6a5974de";//_jyd_4@ 的md5值  收款

public static final String sys_config_kcp_num="71d65436a57b052c4143ddf1e524a1fd"; //_jyd_kcp@ 的md5值
public static final String sys_config_discount_num="d7290fa7128dbb0754fc78eac5a86ecb"; //_jyd_discount@ 的md5值
public static final String sys_config_company_num="671a2384d772eaf328bcc58f9e6c7050"; //_jyd_company@ 的md5值
public static final String sys_config_type_num="aca870d70e8b1e369403bf5a71c9f1fb"; //_jyd_type@ 的md5值
public static final String sys_config_unit_num="53cd638b8e3adc0db32d3b5ab4aa45ff"; //_jyd_unit@ 的md5值
public static final String sys_config_size_num="07b49af152f5d624e643c54a66b53cda"; //_jyd_size@ 的md5值

public static final int SUCCESS=0;
public static final  String ROLE_COOKIE_FLAG="_r";
public static final  String P_COOKIE_FLAG="_p";
public static Map<String ,List> permissionsMap = new ConcurrentHashMap<String ,List>(20);//映射登录者的权限
public static Map<String ,UserInfor> loginUserMap = new ConcurrentHashMap<String ,UserInfor>(5);
public static Map<String ,Long> userLoginTime = new ConcurrentHashMap<String ,Long>(5);//记录用户的登录时间，每次操作自动续期
public static int COOKIE_EXPIRES_TIME =1800;
public static final int PAGE_SIZE=10;
public static final int MIN_PAGE_SIZE=6;
public static final int MAX_PAGE_SIZE=16;
public static final String NUM_SPLIT="-";
public static final String LOCALHOST_IP="127.0.0.1";
public static final String HAS_PAY="1";//已收款
public static final String DELAY_PAY="2";//记账，延迟付款
public static final String NO_PAY="0";//未收款

public static final String NORMAL="0";//正常
public static final String CANCEL="1";//作废
public static String ADMIN_ID="1322d022-a232-45f1-b1bf-9507ab2a95b6";
public static String menu_index_flist="flist";//前台清单
public static String menu_index_usermgt="umgt";//用户管理
public static String menu_index_custmgt="cmgt";//客户管理
public static String menu_index_syscfg="cfg";//系统配置
public static String menu_index_log="log";//操作日志
public static String menu_index_alllist="all";//汇总报表
public static String DAY_START_TIME=" 00:00:00";
public static String DAY_END_TIME=" 23:59:59";

}
