create database wuliSecondHand;
use wuliSecondHand;

CREATE TABLE products(
  id VARCHAR(100),			/*主键*/
  title VARCHAR(40),		/*商品名*/
  description varchar(256), /*描述*/
  price DOUBLE,				/*商品价格*/
  category VARCHAR(40),		/*商品分类*/
  isbargain boolean,		/*是否接受砍价*/
  ischange boolean,			/*是否以物换物*/
  imgurl VARCHAR(100),		/*商品图片地址*/
  PRIMARY KEY (id)
);
