package org.andy.work.admin.permission;

/*
 *管理员编辑的权限 *
 */

public class OperationType {

	/*
	 * 公共部分，用户和公司都拥有的权限
	 */
	public static final String VIEW = "VIEW"; 	//查看
	public static final String UPDATE="UPDATE";//更新
	
	/*
	 * 仅公司拥有的特权
	 */
	public static final String DELETE = "DELETE";//删除
	public static final String ADD = "ADD"; //增加
	public static final String TRADE = "TRADE"; //金额
	public static final String BUY = "BUY";//公司发布的商品
	public static final String NEED = "NEED";//公司发布的采购
	
}
