create table if not exists messages (GW_ID varchar(255), GW_TIMESTAMP long, GW_PAYLOAD varchar(2048), STATUS varchar(255), SOURCE_CODE varchar(512), STREAM_CODE varchar(512), ERROR varchar(1024));
create table if not exists events (GW_ID varchar(255), GW_TIMESTAMP  long, GW_STATUS varchar(255), 
								  SOURCE_CODE varchar(512), STREAM_CODE varchar(512),IS_APPLICATION boolean,VALUES_JSON varchar(4096), ERROR varchar(1024));
commit;