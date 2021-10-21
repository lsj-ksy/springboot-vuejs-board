---
Springboot-Vue.js-Board
===
***

## 🔗 Link
- **Github Repository :** https://github.com/hi-sooyeon/youtube-top10


## 📖 Summary
> ```Introduce Project```
>  - [📌 Features](#-features)
>  - [🛠 Stack](#-stack)
>  - [💿 Installation](#-installation)
>  - [📸 Site Screenshot](#-site-screenshot)
#
* * *
#
# Introduce Project
Vue.js 3와 YouTube Data API를 이용하여 선택한 지역을 선택한 언어로 볼 수 있는 TOP-10 페이지 입니다.

카테고리 별 필터링 및 바로 영상 재생 가능하며 모바일 화면에서도 확인하실 수 있습니다.
<br><br/>
## 📌 Features
- TOP-10 페이지 진입 시, 접속한 지역-언어 자동 셋팅
- 지역, 언어 변경 가능
- 선택한 지역의 카테고리 별 TOP-10 확인 가능
- TOP-10 페이지 내에서 영상 재생 가능
  <br><br/>
## 🛠 Stack
### ✔️ Front-End
- Vue.js 3 ^3.0.0
- Vue-Router 4 (For Vue.js 3) ^4.0.0-0
- Vuex 4 (For Vue.js 3) ^4.0.2
- Vuex-Persistedstate 4 (For Vue.js 3) ^4.0.0
- axios ^0.21.1
- Bootstrap ^5.0.2
- vue-cli-plugin-yaml ^1.0.2

### ✔️ Open API
- YouTube Data API v3
  <br><br/>
## 💿 Installation

### Project clone
```
git clone https://github.com/hi-sooyeon/vue-1week.git
```

### API key & `config.yml` File settings
Before starting you need a Google API key from the [developer console](http://console.developers.google.com/), once you obtained your key, import the module in your application and register it as plugin:

**# Needed Google API**
```
YouTube Data API v3
```

**# Project root > config.yml**
```
googleKey: <YOUR_KEY>
```

### Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```
<br><br/>
## 📸 Site Screenshot
