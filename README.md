**作者：wffger 版本：1.0**    

***点击扫码***    
*    直接扫描单车二维码信息，解析内容后跳转到“编辑记录”    

***编辑记录***    
*    记录二维码信息中的身份编号，记录人，记录地址，违规内容。其中后三项使用SharedPreferences保存历史记录。  
  
***备份列表***    
*     以天为单位创建qb_yymmdd.txt文本，保存在存储卡下的QrBike目录下。内容为编辑记录的内容，但是记录人后增加记录时间信息。    
*    点击文件可以选择上传或者查看。上传时调用QrBikeWS的insertVio方法，将文本内容按行读取，往服务器数据库插入记录。    

***查看信息***    
*     输入记录主键作为参数传递给调用方法，获取字段为可选，指定放回特定字段。
