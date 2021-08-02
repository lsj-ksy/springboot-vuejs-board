import axios from 'axios';
//axios.defaults.baseURL = 'https://nid.naver.com';
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export default {
    methods: {
        async $api(url, method, params) {
            return (await axios({
                method: method,
                url,
                params
            }).catch(e => {
                console.log(e);
            })).data;
        }
    }
}

// Mixins 파일 생성
// 다수의 컴포넌트에서 공통으로 사용하는 함수는 mixins에 등록해서 사용해야 함.