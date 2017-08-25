-- ----------------------------
-- table structure for tbl_pro_current
-- ----------------------------
drop table tbl_pro_current;

create table tbl_pro_current (
  height number(12) not null,
  txncount number(11) not null
);
insert into tbl_pro_current(height,txncount) values(0,0);
-- ----------------------------
-- records of tbl_pro_current
-- ----------------------------

-- ----------------------------
-- table structure for tbl_pro_node
-- ----------------------------
drop table tbl_pro_node;

create table tbl_pro_node (
  id char(255)   null,
  url varchar2(255)   null,
  nodetype char(1)   null,
  block_status char(1)    null,
  rpc_status char(1)   null,
  updatetime varchar2(20)   null,
  system_info varchar2(255)   null,
  running_time varchar2(255)   null
);

-- ----------------------------
-- table structure for tbl_pro_blocks
-- ----------------------------
drop table tbl_pro_blocks;

create table tbl_pro_blocks (
height number(12) not null,
hash varchar2(64)  not null,
prevblock varchar2(64)  null,
nextblock varchar2(64) null,
merkleroot varchar2(64)  null,
blocktime number(11) not null,
nonce number(20)   null,
miner varchar2(35)   null,
txnum number(2)  null,
fee decimal(12,0)  null,
blocksize number(10) not null,
primary key (height) 
);
-- ----------------------------
-- table structure for tbl_pro_txn_details
-- ----------------------------
drop table tbl_pro_txn_details;

create table tbl_pro_txn_details (
txid varchar2(64) not null,
txtype number(4) not null,
scripthash varchar2(255) null,
txtime char(14) not null,
txflag char(1) null,
height number(12) not null,
amount decimal(12,0) null,
fee decimal(12,0) null,
assetid varchar2(255) null,
assetname varchar2(255) null,
txindex number(2) null,
prevhash varchar2(255) null,
previndex varchar2(255) null,
tohash varchar2(255) null,
cakey varchar2(255) null,
spendflag number(1) null,
counterparty varchar2(255) null,
description  varchar2(255) null,
indexinblock  number(10) not null
);
-- ----------------------------
-- table structure for tbl_pro_assets
-- ----------------------------
drop table tbl_pro_assets;

create table tbl_pro_assets (
assetid varchar2(64) not null,
assetname varchar2(255) not null,
issue varchar2(255) not null,
amount decimal(12,0) not null,
assetdesc varchar2(255) not null,
controller varchar2(255) not null,
precisionlen number(4) not null,
height number(12) not null,
cakey varchar2(255) null,
primary key (assetid) 
);
-- ----------------------------
-- table structure for tbl_pro_machine
-- ----------------------------
drop table tbl_pro_machine;

create table tbl_pro_machine (
now_date varchar2(255) null,
node_ip varchar2(255) null,
cpu_usedper varchar2(255) null,
mem_usedper varchar2(255) null,
dis_usedper varchar2(255) null 
);

-- ----------------------------
-- table structure for VIEW_PRO_TRANSACTIONS
-- ----------------------------
drop view "VIEW_PRO_TRANSACTIONS" ;
create view "VIEW_PRO_TRANSACTIONS" as (
	select
		distinct("TXID"), "HEIGHT","TXTYPE","TXTIME","CAKEY","ASSETID","ASSETNAME","INDEXINBLOCK"
	from
		"TBL_PRO_TXN_DETAILS" 
	
);
