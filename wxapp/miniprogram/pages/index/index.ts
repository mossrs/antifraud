import Toast from '@vant/weapp/toast/toast';
import { QuizItem } from '../home/home';

export interface Result {
  code: number;
  msg: string;
  data: QuizItem[] | string;
}

Page({
  data: {
    studentCard: '',
    password: ''
  },

  login() {
    wx.request({
      url: 'https://logosw.top/antifraud/students/login',
      data: this.data,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: response => {
        if(response.statusCode === 200) {
          const { code, msg } = response.data as Result;
          if(code === 1) {
            Toast.success('登录成功');
            setTimeout(() => wx.switchTab({ url: '../home/home' }), 1500);
          } else Toast.fail(msg);
        }
      }
    })
  }
})
