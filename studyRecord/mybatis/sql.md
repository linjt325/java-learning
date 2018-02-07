- [Mybatis](#mybatis)
    - [数据库  返回](#%E6%95%B0%E6%8D%AE%E5%BA%93-%E8%BF%94%E5%9B%9E)


[返回页首](#mybatis)
# myBatis 

## 数据库  

创建表

1. 一条指令对应一条内容
```sql
CREATE TABLE `wechat`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `command` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `content` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = '自动回复内容 ';


```

2. 一条指令对应多条内容(1对多)
```sql
--指令表
 CREATE TABLE `wechat`.`command` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = '自动回复-指令 ';
--指令对应内容的表
CREATE TABLE `wechat`.`command_content` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `commandId` INT not NULL,
  `content` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = '自动回复-指令对应内容 ';
```
