#use this for the code on github
create database db_notify;
use db_notify;
create table customers (customerKey varchar(10) primary key, customerType int, username varchar(50), passcode varchar(50), balance double, accountID int);
ALTER TABLE customers ALTER customerType SET DEFAULT NULL
#end

create database db_notifications;
use db_notifications;
create table notifications (notification_id int auto_increment primary key, customer_idKey, isEmail bool, templateID int, isVisible bool, isRead bool, dateReceived date, dataHook json);
create table customers (cusomter_idKey varchar(10) primary key, accountID int auto_increment primary key, customerType int, username varchar(50), passcode varchar(50), balance double, unreadCount int);
create table templates (templateID int auto_increment primary key, subj varchar(1000), body varchar(1000));

insert into templates (subj, body) values ("Your email address was updated", "Success!\n\nYou updated your email address on <date>\n\nWe’re providing this notification for your account security, and no additional action is required.\n\nIf you did not make this change, please visit our Information Protection Center.\n\nThanks for choosing Capital One®.");
insert into templates (subj, body) values ("You're projected to owe a balance when your loan matures on <date>", "Re: Your auto account ending in <last 4 digits accountNumber>\n\n<customer Name>,\n\nDue to events that occurred during the life of your loan, you’re projected to have a balance due when your loan matures on <date>. You can choose to lower the balance due at the end of your loan now by making an additional one-time payment, or adding a little extra to your current monthly payments.\n\nNot sure how this happened? Activate your account in a few easy steps to check out our Auto Loan Tracker and see more details.");

insert into notifications (isEmail, templateID, isVisible, isRead) values (true, 1, true, false);

insert into customers (username, passcode, balance, unreadCount) values ("ronaldjones", "alakdjs93", 5450.95, 14);
insert into customers (username, passcode, balance, unreadCount) values ("tombrady", "afghddh", 5855.69, 9);
insert into customers (username, passcode, balance, unreadCount) values ("mikeevans", "dfghdfmdgh", 8879.95, 0);
insert into customers (username, passcode, balance, unreadCount) values ("khalidhossain", "gjygj6", 5245.95, 2);
insert into customers (username, passcode, balance, unreadCount) values ("khalilmack", "xfgndr6", 4533.95, 4);
insert into customers (username, passcode, balance, unreadCount) values ("davidbeckham", "nfghmfg6", 5450.95, 10);
insert into customers (username, passcode, balance, unreadCount) values ("johndoe", "xbdbr5", 7895.95, 11);
insert into customers (username, passcode, balance, unreadCount) values ("jasonsmith", "bsfgbt6", 4458.95, 12);
insert into customers (username, passcode, balance, unreadCount) values ("tylerlockett", "serr5", 5214.95, 7);
insert into customers (username, passcode, balance, unreadCount) values ("jameswhite", "svdf3", 9634.95, 8);
insert into customers (username, passcode, balance, unreadCount) values ("tylerjohnson", "sdfsbdfb4", 8874.95, 2);
insert into customers (username, passcode, balance, unreadCount) values ("dennisrodman", "gsdfgjn5", 6521.95, 1);


drop table notification;