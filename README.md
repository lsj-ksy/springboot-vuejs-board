---
Springboot-Vue.js-Board
===
***

## 🔗 Link
- **Deploy Site  :** http://team1.space/springboot-vuejs-board/ &ensp; <img src="https://img.shields.io/website?url=http%3A%2F%2Fteam1.space%2Fspringboot-vuejs-board%2F" />


- **Github Repository :** https://github.com/lsj-ksy/springboot-vuejs-board


## 📖 Summary
> ```Introduce Project```
>  - [📌 Features](#-features)
>  - [🛠 Stack](#-stack)
>  - [💿 Installation](#-installation)
>  - [📸 Site Screenshot](#-site-screenshot)
>  - [🧑🏻‍💻 Team Member](#-team-member)
#
* * *
#
# Introduce Project
Spring Boot 와 Vue.js를 사용하여 만든 기본형 게시판 2인 프로젝트 입니다.

게시판 카테고리 구분과 카테고리별 게시글 정렬, 페이징, 파일 업로드 및 미리보기, 댓글 등 Basic한 CRUD를 구현하였습니다.
<br><br/>
## 📌 Features
- Vue.js를 활용한 SPA 방식의 사이트 구현
- Bootstrap 5.0.2 를 활용한 사이트 디자인 및 modal 기능 구현
- JPA 를 활용한 DB 개발
- Restful api 구현
- npm 을 활용한 페이징처리
- Gradle 빌드 도구 사용
- 게시글, 댓글에 관련한 기본적인 CRUD 구현
- 게시글 파일 미리보기, 업로드, 다운로드 구현
  <br><br/>
## 🛠 Stack
### ✔️ Front-End
- Vue.js 3 ^3.0.0
- Vue-Router 4 (For Vue.js 3) ^4.0.0-0
- Vuex 4 (For Vue.js 3) ^4.0.2
- axios ^0.21.1
- Bootstrap ^5.0.2
- vue-sweetalert2 ^5.0.2
- v-pagination-3 ^0.1.6
- ckeditor5-vue ^2.0.1

### ✔️ Back-End
- Java v1.8
- Gradle v7.1.1
- Spring Boot v2.5.3
- Spring Boot Starter Data JPA v2.5.3
- Spring Boot Starter Validation v2.5.3
- Apache Commons IO v2.11.0
- MariaDB Java Client v2.7.3

### ✔️ DataBase
- MariaDB v10.5.10
  <br><br/>
## 💿 Installation
```
# clone our repo
git clone https://github.com/lsj-ksy/springboot-vuejs-board.git
```

### `application.properties` File settings
>> src/main/resources/```application.proterties```
>   ~~~
> spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
> spring.datasource.url=<YOUR_DB_URL>
> spring.datasource.username=<YOUR_DB_USERNAME>
> spring.datasource.password=<YOUR_DB_PASSWORD>
> 
> spring.jpa.hibernate.ddl-auto=update
> logging.level.org.hibernate.sql=DEBUG
> spring.jpa.show-sql=true
> spring.jpa.properties.hibernate.format_sql=true
> 
> spring.servlet.multipart.max-file-size=10MB
> spring.servlet.multipart.max-request-size=20MB
>   ~~~

```
# change directory to our repo
cd springboot-vuejs-board

# start project
./gradlew bootRun

# build
./gradlew bootJar
java -jar SpringVue-0.0.1-SNAPSHOT.jar
```
<br><br/>
## 📸 Site Screenshot


## 🧑🏻‍💻 Team Member
- 김수연 : https://github.com/hi-sooyeon
- 이승준 : https://github.com/Zagg2732

<br><br/>
