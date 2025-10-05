import axios from 'axios';

class AxiosInstance {
    
    // 帮我写一个axios的封装文件，文件名是axios.ts，内容是创建一个axios实例，并且根据传入的参数决定是使用模拟数据还是使用真实接口地址，模拟数据的地址是https://m1.apifoxmock.com/m1/7119507-6842329-default/，真实接口地址是/api，返回这个axios实例
    static createInstance(useMock: boolean) {
        if (useMock) {
            return axios.create({
                baseURL: 'https://m1.apifoxmock.com/m1/7119507-6842329-default/',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
        } else {
            return axios.create({
                baseURL: '/api', // 这里换成你的真实接口地址
                headers: {
                    'Content-Type': 'application/json',
                },
            });
        }
    }

    static getInstance(useMock: boolean) {
        return this.createInstance(useMock);
    }

    // 导出一个get,post,put,delete方法
    static get(url: string, useMock: boolean, config = {}) {
        const instance = this.getInstance(useMock);
        return instance.get(url, config);
    }
    static post(url: string, useMock: boolean, data = {}, config = {}) {
        const instance = this.getInstance(useMock);
        return instance.post(url, data, config);
    }
    static put(url: string, useMock: boolean, data = {}, config = {}) {
        const instance = this.getInstance(useMock);
        return instance.put(url, data, config);
    }
    static delete(url: string, useMock: boolean, config = {}) {
        const instance = this.getInstance(useMock);
        return instance.delete(url, config);
    }

    // 添加响应拦截器
    static addResponseInterceptor(instance: any) {
        instance.interceptors.response.use(
            (response: any) => {
                // 对响应数据做点什么
                return response.data;
            },
            (error: any) => {
                // 对响应错误做点什么
                return Promise.reject(error);
            }
        );
    }
 
    
}

export default AxiosInstance;