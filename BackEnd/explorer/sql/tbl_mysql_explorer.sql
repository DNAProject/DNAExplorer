
-- ----------------------------
-- table structure for tbl_pro_current
-- ----------------------------
drop table if exists tbl_pro_current;

create table tbl_pro_current (
  height int(12) not null,
  txncount int(11) not null
);

insert into tbl_pro_current(height,txncount) values(0,0);

-- ----------------------------
-- records of tbl_pro_current
-- ----------------------------

-- ----------------------------
-- table structure for tbl_pro_node
-- ----------------------------
drop table if exists tbl_pro_node;

create table tbl_pro_node (
  id char(255)   null,
  url varchar(255)   null,
  nodetype char(1)   null,
  block_status char(1)    null,
  rpc_status char(1)   null,
  updatetime varchar(20)   null,
  system_info varchar(255)   null,
  running_time varchar(255)   null
);

-- ----------------------------
-- table structure for tbl_pro_blocks
-- ----------------------------
drop table if exists tbl_pro_blocks;

create table tbl_pro_blocks (
height int(11) not null,
hash varchar(64)  not null,
prevblock varchar(64)  null,
nextblock varchar(64) null,
merkleroot varchar(64)  null,
blocktime int(11) not null,
nonce varchar(20)   null,
miner varchar(35)   null,
txnum int(10)  null,
fee decimal(12,0)  null,
blocksize int(11) not null,
primary key (height) 
);

-- ----------------------------
-- table structure for tbl_pro_txn_details
-- ----------------------------
drop table if exists tbl_pro_txn_details;

create table tbl_pro_txn_details (
txid varchar(64) not null,
txtype int(4) not null,
scripthash varchar(255) null,
txtime char(14) not null,
txflag char(1) null,
height int(12) not null,
amount decimal(12,0) null,
fee decimal(12,0) null,
assetid varchar(255) null,
assetname varchar(255) null,
txindex int(2) null,
prevhash varchar(255) null,
previndex varchar(255) null,
tohash varchar(255) null,
cakey varchar(255) null,
spendflag int(1) null,
counterparty varchar(255) null,
description  varchar(255) null,
indexinblock int(10)
);

-- ----------------------------
-- table structure for tbl_pro_assets
-- ----------------------------
drop table if exists tbl_pro_assets;

create table tbl_pro_assets (
assetid varchar(64) not null,
assetname varchar(255) not null,
issue varchar(255) not null,
amount decimal(12,0) not null,
assetdesc varchar(255) not null,
controller varchar(255) not null,
precisionlen int(4) not null,
height int(12) not null,
cakey varchar(255) null,
primary key(assetid)
);

-- ----------------------------
-- table structure for tbl_pro_machine
-- ----------------------------
drop table if exists tbl_pro_machine;

create table tbl_pro_machine (
now_date varchar(255) null,
node_ip varchar(255) null,
cpu_usedper varchar(255) null,
mem_usedper varchar(255) null,
dis_usedper varchar(255) null 
);

-- ----------------------------
-- table structure for view_pro_transactions
-- ----------------------------
drop view if exists view_pro_transactions;

create view view_pro_transactions 
as select distinct(a.txid) as txid, a.height as height,a.txtype as txtype,a.txtime as txtime,a.cakey as cakey,a.description as description,
a.assetid as asserid,a.assetname as assetname,a.indexinblock as indexinblock
from tbl_pro_txn_details a;


