# hibernate-tutorial3
Hibernate Annotations

[![contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg?style=flat)](https://github.com/nfriaa/hibernate-tutorial3/issues) [![Travis](https://img.shields.io/travis/rust-lang/rust.svg)](https://github.com/nfriaa/hibernate-tutorial3) [![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/nfriaa/hibernate-tutorial3/blob/master/LICENSE)

## Description
A sample code to use Hibernate in **Annotations** mode
* JavaSE 8
* Hibernate 5 / Annotations
* Maven 4
* MySQL 5

## 1. Database (the same in Tutorial 2)
We will use the same database / table

## 2. Maven "pom.xml" dependencies
```
<dependencies>
    <!-- MySQL connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>6.0.6</version>
    </dependency>
    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.2.11.Final</version>
    </dependency>
</dependencies>
```

## 3. Changes in "hibernate.cfg.xml"
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- MySQL -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/persist_db</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">root</property>

        <!-- Hibernate -->
        <property name="show_sql">true</property>
        <property name="format_sql">false</property>
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Entities -->
        <mapping class="net.isetjb.hibernatetutorial3.Product"/>
    </session-factory>
</hibernate-configuration>
```
* hibernate.hbm2ddl.auto : update => update the database schema with defined entities
* hibernate.hbm2ddl.auto : create => creates the schema necessary for defined entities, destroying any previous data

## 3. No "Product.hbm.xml" file
There is no "Product.hbm.xml" mapping file. To do mapping we will use Hibernate **Annotations** instead

## 4. Annotations in POJO classes "Product.java"
```
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 255, nullable = true)
    private String name;

    @Column(name = "price", nullable = true)
    private int price;

    // GETTERs and SETTERs here...
}
```
* @Entity : link to the "Product" class (Bean / POJO)
* @Table : table that will be used to persist the entity in the database
* @Id : primary key
* @GeneratedValue : generation type for the primary key (ex : AUTO_INCREMENT in MySQL)
* @Column : details of the column to which a property will be mapped

## 5. Class "Application.java"
Use exactly "Application.java" in Tutorial 2 to test