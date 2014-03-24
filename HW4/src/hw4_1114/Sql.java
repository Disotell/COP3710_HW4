package hw4_1114;
import java.sql.*;

public class Sql {
	Connection conn;
	
	Sql(){
		connect();
		create();
	}
	
	public void close() {
		try {
			conn.close(); // disconnects from database
			System.out.println("Closing Database Connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:" + // Protocol
					System.getProperty("user.dir") + "/movies", // db file path
					"sa", // user
					""); // password

			// conn.close(); // disconnects from database
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void create(){
			try {
				Statement statement = conn.createStatement();
				
				/* begin table creation */
				statement.execute("create table department(dept_id smallint unsigned not null auto_increment,name varchar(20) not null,constraint pk_department primary key (dept_id));");
				
				statement.execute("create table branch(branch_id smallint unsigned not null auto_increment,name varchar(20) not null,address varchar(30),city varchar(20),state varchar(2),zip varchar(12),constraint pk_branch primary key (branch_id));");
				
				statement.execute("create table employee(emp_id smallint unsigned not null auto_increment,fname varchar(20) not null,lname varchar(20) not null,start_date date not null,end_date date,superior_emp_id smallint unsigned,dept_id smallint unsigned,title varchar(20),assigned_branch_id smallint unsigned,constraint fk_e_emp_id foreign key (superior_emp_id) references employee (emp_id),constraint fk_dept_id foreign key (dept_id) references department (dept_id),constraint fk_e_branch_id foreign key (assigned_branch_id) references branch (branch_id),constraint pk_employee primary key (emp_id));");

				statement.execute("create table product_type(product_type_cd varchar(10) not null,name varchar(50) not null,constraint pk_product_type primary key (product_type_cd));");

				statement.execute("create table product(product_cd varchar(10) not null,name varchar(50) not null,product_type_cd varchar(10) not null,date_offered date,date_retired date,constraint fk_product_type_cd foreign key (product_type_cd)references product_type (product_type_cd),constraint pk_product primary key (product_cd));");
				
				statement.execute("create table customer(cust_id integer unsigned not null auto_increment,fed_id varchar(12) not null,cust_type_cd varchar(1) check((cust_type_cd in ('I','B')) and (not null)),address varchar(30),city varchar(20),state varchar(20),postal_code varchar(10),constraint pk_customer primary key (cust_id));");
				
				statement.execute("create table individual(cust_id integer unsigned not null,fname varchar(30) not null,lname varchar(30) not null,birth_date date,constraint fk_i_cust_id foreign key (cust_id)references customer (cust_id),constraint pk_individual primary key (cust_id));");
				
				statement.execute("create table business(cust_id integer unsigned not null,name varchar(40) not null,state_id varchar(10) not null,incorp_date date,constraint fk_b_cust_id foreign key (cust_id)references customer (cust_id),constraint pk_business primary key (cust_id));");
				
				statement.execute("create table officer(officer_id smallint unsigned not null auto_increment,cust_id integer unsigned not null,fname varchar(30) not null,lname varchar(30) not null,title varchar(20),start_date date not null,end_date date,constraint fk_o_cust_id foreign key (cust_id)references business (cust_id),constraint pk_officer primary key (officer_id));");
				
				statement.execute("create table account(account_id integer unsigned not null auto_increment,product_cd varchar(10) not null,cust_id integer unsigned not null,open_date date not null,close_date date,last_activity_date date,status varchar(10) check(status in ('ACTIVE','CLOSED','FROZEN')),open_branch_id smallint unsigned,open_emp_id smallint unsigned,avail_balance decimal(10,2),pending_balance decimal(10,2),constraint fk_product_cd foreign key (product_cd)references product (product_cd),constraint fk_a_cust_id foreign key (cust_id)references customer (cust_id),constraint fk_a_branch_id foreign key (open_branch_id)references branch (branch_id),constraint fk_a_emp_id foreign key (open_emp_id)references employee (emp_id),constraint pk_account primary key (account_id));");
				
				statement.execute("create table transaction(txn_id integer unsigned not null auto_increment,txn_date datetime not null,account_id integer unsigned not null,txn_type_cd varchar(5) check (txn_type_cd in ('DBT','CDT')),amount decimal(10,2) not null,teller_emp_id smallint unsigned,execution_branch_id smallint unsigned,funds_avail_date datetime,constraint fk_t_account_id foreign key (account_id)references account (account_id),constraint fk_teller_emp_id foreign key (teller_emp_id)references employee (emp_id),constraint fk_exec_branch_id foreign key (execution_branch_id)references branch (branch_id),constraint pk_transaction primary key (txn_id));");
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

}
