package cn.zhr.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.zhr.base.BaseTest;
import cn.zhr.util.SystemConfig;

public class JDBCTest extends BaseTest {

	@Before
	public void before() {
		System.out.println("========Start========");
	}
	@After
	public void after() {
		System.out.println("========Start========");
	}

	@Test
	public void test1() {
//		String creatSql = "DROP TABLE IF EXISTS `";
	String tableSql = "CREATE TABLE `";
	String core = "` ("+
"`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',"+
"`order_no` varchar(50) DEFAULT '' COMMENT '按照规则生成  订单号',"+
 " `customer_name` varchar(20) DEFAULT '' COMMENT '用户名字',"+
"  `customer_id` bigint(20) DEFAULT NULL COMMENT '消费者 id',"+
"  `store_name` varchar(50) DEFAULT '' COMMENT '商户名称',"+
 " `store_id` bigint(20) DEFAULT NULL COMMENT '商户 ID',"+
 " `order_validate_time` datetime DEFAULT NULL COMMENT '订单有效期',"+
 " `ac_no` varchar(500) DEFAULT '' COMMENT '订单成功后返回账号',"+
 " `gathering` decimal(10,2) DEFAULT '0.00' COMMENT '应收金额,应付金额',"+
 " `deduction_integral` int(9) DEFAULT NULL COMMENT '抵扣积分',"+
"  `deduction_money` decimal(10,2) DEFAULT '0.00' COMMENT '抵扣金额',"+
"  `award_integral` int(9) DEFAULT NULL COMMENT '获赠积分',"+
"  `customer_app` int(2) DEFAULT NULL COMMENT '用户是否评论 1:已评论    0/空:未评论',"+
"  `order_date` datetime DEFAULT NULL COMMENT '订购日期',"+
 " `status` int(2) DEFAULT NULL COMMENT '订单状态(0:待支付   1:已支付   2:已退款   3:过期退款  4:待验证  5:成功订单)',"+
"  `total_cost` decimal(10,2) DEFAULT '0.00' COMMENT '订单总金额',"+
 " `concessions_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',"+
  "`store_phone` varchar(20) DEFAULT '' COMMENT '商户电话',"+
  "`red_packet_money` decimal(10,2) DEFAULT '0.00' COMMENT '红包金额',"+
 " `red_packet_id` bigint(20) DEFAULT NULL COMMENT '红包ID',"+
"  `address_id` bigint(20) DEFAULT NULL COMMENT '地址ID',"+
 " `address` varchar(100) DEFAULT '' COMMENT '详细地址',"+
"  `product_cost` decimal(10,2) DEFAULT '0.00' COMMENT '产品金额',"+
 " `logistics_no` varchar(100) DEFAULT '' COMMENT '物流单号',"+
"  `logistics_code` varchar(100) DEFAULT '' COMMENT '物流公司code',"+
"  `affixation` decimal(10,2) DEFAULT '0.00' COMMENT '运费',"+
 " `cancel_reason` varchar(200) DEFAULT '' COMMENT '取消原因',"+
 " `contact` varchar(50) DEFAULT '' COMMENT '收货人',"+
"  `contact_phone` varchar(20) DEFAULT '' COMMENT '收货人电话',"+
"  `pay_type` int(2) unsigned DEFAULT '0' COMMENT '付款方式 1微信支付 2 支付宝支付 3 百度钱包 4 京东钱包 5 银联快捷支付 0 ryhxPay',"+
 " `remark` varchar(200) DEFAULT '' COMMENT '订单备注',"+
"  `type` int(2) DEFAULT NULL COMMENT '订单类型， 0 : 线上虚拟订单    1 : 线上实物订单   2 : 买单订单',"+
"  `qrcord_img` varchar(100) DEFAULT '' COMMENT '二维码图片',"+
 " `qrcord_content` varchar(100) DEFAULT '' COMMENT '二维码图片地址',"+
"  `check_status` int(2) DEFAULT NULL COMMENT '商户是否扫描二维码  0 未扫描  1 扫描过了 空 二维码还未生成',"+
 " `parent_id` varchar(32) DEFAULT '' COMMENT '父订单ID',"+
 " `shopping_id` bigint(20) DEFAULT NULL COMMENT '活动Id 订单所处活动',"+
"  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',"+
 " `is_comment` int(2) DEFAULT NULL COMMENT '商户是否评论    0:未评    1：已评',"+
"  `online_pay_no` varchar(50) DEFAULT '' COMMENT '在线交易流水号存放字段',"+
 " `is_delete` int(2) DEFAULT NULL COMMENT '是否删除：0： 正常，1：删除',"+
"  `validate_time` datetime DEFAULT NULL COMMENT '验证时间(扫描二维码)',"+
" `refund_time` datetime DEFAULT NULL COMMENT '退款时间',"+
 " `is_recon` int(2) DEFAULT NULL COMMENT '是否对账( 0:未对账   1:已对账)',"+
 " `without_discount_amount` decimal(10,2) DEFAULT NULL COMMENT '不参与优惠金额',"+
 " `shopping_name` varchar(50) DEFAULT '' COMMENT '活动名称 (冗余字段) 方便查询 ',"+
 " `offer_id` varchar(100) DEFAULT '' COMMENT '普惠,特惠 活动Id 复合字段 (普惠 p  ; 特惠 s)',"+
 " `offer_name` varchar(200) DEFAULT '' COMMENT '普惠或特惠 活动名称',"+
 " PRIMARY KEY (`id`)"+
") ENGINE=InnoDB AUTO_INCREMENT=1054289 DEFAULT CHARSET=utf8 COMMENT='订单核心表';";
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册jdbc驱动
			Class.forName(SystemConfig.driver);
			// 打开连接
			System.out.println("//连接数据库");
			conn = DriverManager.getConnection(SystemConfig.url, SystemConfig.username, SystemConfig.password);
			// 执行创建表
			System.out.println("//创建表");
			stmt = conn.createStatement();
			String param = "omorder";
			String creatsql = tableSql+param+core;
			if (0 == stmt.executeLargeUpdate(creatsql)) {
				System.out.println("成功创建表！");
			} else {
				System.out.println("创建表失败！");
			}
			//
			stmt.close();
			conn.close();
			System.out.println("//关闭资源");
		} catch (Exception e) {
			System.out.println("创建表失败！");
			e.printStackTrace();
		}
	}
}
