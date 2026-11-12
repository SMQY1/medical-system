-- =============================================================
-- 医疗系统数据库设计（实验二）
-- 设计说明：
--   1. 五张表：科室 department、医生 doctor、患者 patient、
--      挂号记录 registration、电子处方 prescription。
--   2. 遵循第三范式：每张表只保存自身属性，科室名称、医生姓名、
--      患者姓名等均通过外键关联获取，不做冗余存储；
--      挂号表中的科室由医生所属科室唯一确定，故只保留 doctor_id。
--   3. 约束：主键自增；非空字段显式声明；业务唯一键使用唯一索引；
--      表间关系使用外键约束（ON DELETE RESTRICT 防止误删主数据）。
--   4. 逻辑删除字段 deleted（0 正常，1 已删除），与 MyBatis-Plus 保持一致。
-- 执行方式：mysql -uroot -p --default-character-set=utf8mb4 < medical_db.sql
-- =============================================================

CREATE DATABASE IF NOT EXISTS medical_db
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE medical_db;

-- 先删子表，再删父表，避免外键依赖导致删除失败
DROP TABLE IF EXISTS prescription;
DROP TABLE IF EXISTS registration;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS department;

-- -------------------------------------------------------------
-- 1. 科室表
-- -------------------------------------------------------------
CREATE TABLE department
(
    id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '科室主键',
    dept_code   VARCHAR(20)  NOT NULL COMMENT '科室编码，业务唯一',
    name        VARCHAR(50)  NOT NULL COMMENT '科室名称',
    location    VARCHAR(100) DEFAULT NULL COMMENT '科室位置',
    phone       VARCHAR(20)  DEFAULT NULL COMMENT '科室联系电话',
    description VARCHAR(255) DEFAULT NULL COMMENT '科室简介',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：1 启用，0 停用',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted     TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除：0 正常，1 已删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_department_code (dept_code),
    UNIQUE KEY uk_department_name (name),
    KEY idx_department_status (status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='科室表';

-- -------------------------------------------------------------
-- 2. 医生表（dept_id 外键引用科室，不冗余科室名称）
-- -------------------------------------------------------------
CREATE TABLE doctor
(
    id           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '医生主键',
    dept_id      BIGINT       NOT NULL COMMENT '所属科室，外键',
    doctor_no    VARCHAR(20)  NOT NULL COMMENT '医生工号，业务唯一',
    name         VARCHAR(50)  NOT NULL COMMENT '姓名',
    gender       CHAR(1)      NOT NULL DEFAULT '男' COMMENT '性别：男/女',
    title        VARCHAR(30)  NOT NULL COMMENT '职称，如主任医师',
    phone        VARCHAR(20)  NOT NULL COMMENT '手机号，业务唯一',
    email        VARCHAR(64)  DEFAULT NULL COMMENT '邮箱',
    introduction VARCHAR(255) DEFAULT NULL COMMENT '擅长领域',
    status       TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：1 在岗，0 离岗',
    create_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除：0 正常，1 已删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_doctor_no (doctor_no),
    UNIQUE KEY uk_doctor_phone (phone),
    KEY idx_doctor_dept (dept_id),
    KEY idx_doctor_name (name),
    CONSTRAINT fk_doctor_department FOREIGN KEY (dept_id) REFERENCES department (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='医生表';

-- -------------------------------------------------------------
-- 3. 患者表
-- -------------------------------------------------------------
CREATE TABLE patient
(
    id                BIGINT       NOT NULL AUTO_INCREMENT COMMENT '患者主键',
    patient_no        VARCHAR(32)  NOT NULL COMMENT '病历号，业务唯一',
    name              VARCHAR(50)  NOT NULL COMMENT '姓名',
    gender            CHAR(1)      NOT NULL DEFAULT '男' COMMENT '性别：男/女',
    birth_date        DATE         DEFAULT NULL COMMENT '出生日期',
    id_card           CHAR(18)     NOT NULL COMMENT '身份证号，业务唯一',
    phone             VARCHAR(20)  NOT NULL COMMENT '手机号，业务唯一',
    address           VARCHAR(200) DEFAULT NULL COMMENT '联系地址',
    emergency_contact VARCHAR(50)  DEFAULT NULL COMMENT '紧急联系人',
    emergency_phone   VARCHAR(20)  DEFAULT NULL COMMENT '紧急联系人电话',
    status            TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：1 正常，0 停用',
    create_time       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted           TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除：0 正常，1 已删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_patient_no (patient_no),
    UNIQUE KEY uk_patient_id_card (id_card),
    UNIQUE KEY uk_patient_phone (phone),
    KEY idx_patient_name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='患者表';

-- -------------------------------------------------------------
-- 4. 挂号记录表（只存患者、医生的外键，科室由医生推导，符合 3NF）
-- -------------------------------------------------------------
CREATE TABLE registration
(
    id          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '挂号主键',
    reg_no      VARCHAR(32)   NOT NULL COMMENT '挂号单号，业务唯一',
    patient_id  BIGINT        NOT NULL COMMENT '患者，外键',
    doctor_id   BIGINT        NOT NULL COMMENT '医生，外键',
    visit_time  DATETIME      NOT NULL COMMENT '就诊时间',
    fee         DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '挂号费',
    status      TINYINT       NOT NULL DEFAULT 0 COMMENT '状态：0 待就诊，1 已就诊，2 已取消',
    remark      VARCHAR(255)  DEFAULT NULL COMMENT '备注',
    create_time DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted     TINYINT       NOT NULL DEFAULT 0 COMMENT '逻辑删除：0 正常，1 已删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_registration_no (reg_no),
    KEY idx_registration_patient (patient_id),
    KEY idx_registration_doctor (doctor_id),
    KEY idx_registration_visit_time (visit_time),
    CONSTRAINT fk_registration_patient FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_registration_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='挂号记录表';

-- -------------------------------------------------------------
-- 5. 电子处方表（通过 registration_id 关联就诊记录）
-- -------------------------------------------------------------
CREATE TABLE prescription
(
    id              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '处方主键',
    presc_no        VARCHAR(32)  NOT NULL COMMENT '处方编号，业务唯一',
    registration_id BIGINT       NOT NULL COMMENT '挂号记录，外键',
    medicine_name   VARCHAR(100) NOT NULL COMMENT '药品名称',
    specification   VARCHAR(50)  DEFAULT NULL COMMENT '规格',
    dosage          VARCHAR(50)  NOT NULL COMMENT '单次用量',
    frequency       VARCHAR(50)  DEFAULT NULL COMMENT '用药频次',
    days            INT          DEFAULT NULL COMMENT '用药天数',
    advice          VARCHAR(255) DEFAULT NULL COMMENT '医嘱',
    create_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted         TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除：0 正常，1 已删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_prescription_no (presc_no),
    KEY idx_prescription_registration (registration_id),
    CONSTRAINT fk_prescription_registration FOREIGN KEY (registration_id) REFERENCES registration (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='电子处方表';

-- -------------------------------------------------------------
-- 初始化测试数据
-- -------------------------------------------------------------
INSERT INTO department (dept_code, name, location, phone, description, status)
VALUES ('NK', '内科', '门诊楼 2 层', '0743-8560001', '诊治呼吸、消化、心血管等内科疾病', 1),
       ('WK', '外科', '门诊楼 3 层', '0743-8560002', '开展普外、骨科等手术治疗', 1),
       ('FK', '妇科', '门诊楼 4 层', '0743-8560003', '妇科常见病与孕产期保健', 1),
       ('EK', '儿科', '门诊楼 2 层', '0743-8560004', '0-14 岁儿童疾病诊治', 1),
       ('YK', '眼科', '门诊楼 5 层', '0743-8560005', '近视防控与眼底疾病诊疗', 1);

INSERT INTO doctor (dept_id, doctor_no, name, gender, title, phone, email, introduction, status)
VALUES (1, 'D001', '李建国', '男', '主任医师', '13800000001', 'lijianguo@hospital.com', '冠心病、高血压诊治', 1),
       (1, 'D002', '王芳', '女', '副主任医师', '13800000002', 'wangfang@hospital.com', '慢性胃炎、消化系统疾病', 1),
       (2, 'D003', '张伟', '男', '主任医师', '13800000003', 'zhangwei@hospital.com', '普外科微创手术', 1),
       (3, 'D004', '刘敏', '女', '副主任医师', '13800000004', 'liumin@hospital.com', '妇科炎症与产前检查', 1),
       (4, 'D005', '陈强', '男', '主治医师', '13800000005', 'chenqiang@hospital.com', '小儿呼吸道感染', 1),
       (5, 'D006', '赵丽', '女', '主治医师', '13800000006', 'zhaoli@hospital.com', '青少年近视防控', 1);

INSERT INTO patient (patient_no, name, gender, birth_date, id_card, phone, address, emergency_contact, emergency_phone, status)
VALUES ('P2026001', '董洋', '男', '2004-05-18', '433101200405180011', '13900000001', '湖南省吉首市人民路 1 号', '董父', '13900009001', 1),
       ('P2026002', '周婷', '女', '1998-09-02', '433101199809020022', '13900000002', '湖南省吉首市武陵东路 8 号', '周母', '13900009002', 1),
       ('P2026003', '黄浩', '男', '1990-03-27', '433101199003270033', '13900000003', '湖南省吉首市团结东路 21 号', '黄妻', '13900009003', 1),
       ('P2026004', '吴静', '女', '2015-11-11', '433101201511110044', '13900000004', '湖南省吉首市砂子坳 5 号', '吴父', '13900009004', 1),
       ('P2026005', '杨帆', '男', '1985-07-08', '433101198507080055', '13900000005', '湖南省吉首市乾州新区 12 号', '杨妻', '13900009005', 1),
       ('P2026006', '林小雨', '女', '2001-01-23', '433101200101230066', '13900000006', '湖南省吉首市人民南路 66 号', '林母', '13900009006', 1),
       ('P2026007', '唐磊', '男', '1979-12-30', '433101197912300077', '13900000007', '湖南省吉首市镇溪街道 3 号', '唐子', '13900009007', 1),
       ('P2026008', '孙悦', '女', '2007-06-15', '433101200706150088', '13900000008', '湖南省吉首市峒河街道 9 号', '孙父', '13900009008', 1);

INSERT INTO registration (reg_no, patient_id, doctor_id, visit_time, fee, status, remark)
VALUES ('R20261104001', 1, 1, '2026-11-04 09:00:00', 20.00, 1, '血压偏高，复查'),
       ('R20261104002', 2, 2, '2026-11-04 09:30:00', 15.00, 1, '胃部不适'),
       ('R20261105001', 3, 3, '2026-11-05 10:00:00', 20.00, 0, '右下腹疼痛'),
       ('R20261105002', 4, 5, '2026-11-05 10:20:00', 15.00, 0, '发热咳嗽'),
       ('R20261105003', 5, 4, '2026-11-05 14:00:00', 15.00, 2, '临时取消'),
       ('R20261106001', 6, 6, '2026-11-06 08:40:00', 10.00, 0, '视力下降');

INSERT INTO prescription (presc_no, registration_id, medicine_name, specification, dosage, frequency, days, advice)
VALUES ('RX20261104001', 1, '苯磺酸氨氯地平片', '5mg*7 片', '1 片', '每日 1 次', 28, '晨起服用，监测血压'),
       ('RX20261104002', 2, '奥美拉唑肠溶胶囊', '20mg*14 粒', '1 粒', '每日 2 次', 14, '饭前半小时服用'),
       ('RX20261105001', 3, '头孢克肟分散片', '0.1g*6 片', '1 片', '每日 2 次', 3, '用药期间禁酒');
