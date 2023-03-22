// pages/home/home.ts
import { Result } from '../index/index';
import Notify from '@vant/weapp/notify/notify';
import Dialog from '@vant/weapp/dialog/dialog';

export interface QuizItem {
  id: string;
  fraudSubjectName: string;
  fraudType: string;
  fraudOptionType: string;
  fraudTag: string;
  answer: string;
  aoption: string;
  boption: string;
  coption: string;
  doption: string;
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    quiz: undefined as unknown as QuizItem[],
    answers: undefined as unknown as Array<string | string[]>,
    selections: undefined as unknown as Array<string | string[] | undefined>,
    loading: true,
    isComplete: false,
    doneNum: 0,
    translateX: 'transform: translateX(-100%)',
    TYPE_COLOR_MAP: {
      '网络诈骗': ['#F56C6C', '#fde2e2'],
      '新型诈骗': ['#E6A23C', '#faecd8'],
      '冒充熟人诈骗': ['#2ecc71', '#e1f3d8'],
      '防范诈骗': ['#409EFF', '#d9ecff'],
      '电信诈骗': ['#626aef', '#eff0fd'],
      '信用卡诈骗': ['#34495e', '#e8eaeb'],
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    wx.hideHomeButton();
    const tabbar = this.getTabBar();
    tabbar.setData({ activeTab: 'home' });
    if(!this.data.quiz) {
      wx.request({
        url: 'https://logosw.top/antifraud/wx/push',
        method: 'GET',
        success: response => {
          if(response.statusCode === 200) {
            const { data, code } = response.data as Result;
            const quizItems: QuizItem[] = data as QuizItem[];
            if(code === 1) setTimeout(() => this.setData({
              quiz: quizItems,
              loading: false,
              answers: quizItems.map(item => item.answer.length === 1 ? item.answer : item.answer.split('')),
              selections: quizItems.map(() => undefined)
            }), 500);
            else Notify({
              message: '题目获取失败',
              color: '#ad0000',
              background: '#ffe1e1',
            });
          }
        }
      })
    }
  },

  onSelectionChange(e: { currentTarget: { dataset: { index: number, name: string } } }) {
    const { index, name } = e.currentTarget.dataset;
    let key = `selections[${index}]`;
    if(this.data.quiz[index].fraudOptionType === '单选题') this.setData({ [key]: name }); 
    else {
      const oldValue = this.data.selections[index] as string[];
      if(!oldValue?.includes(name)) {
        if(oldValue === undefined) this.setData({ [key]: [name] });
        else this.setData({ [key]: oldValue?.push(name) && oldValue });
      } else {
        oldValue?.splice(oldValue.indexOf(name), 1);
        this.setData({ [key]: oldValue });
      }
    }
    const { length } = this.data.quiz;
    let doneNum = 0;
    this.data.selections.map(ele => (ele && ele?.length !== 0) ? doneNum++ : undefined);
    this.setData({ 
      doneNum,
      translateX: `transform: translateX(-${ (1 - doneNum / length ) * 100 }%)`
    });
  },

  onSubmit() {
    if(this.data.doneNum !== this.data.quiz.length) return;
    const beforeClose = (action: string) => new Promise((resolve: (value: boolean) => void) => {
      if(action === 'cancel') resolve(true);
      else setTimeout(() => {
        this.setData({ isComplete: true });
        resolve(true);
        Notify({ type: 'success', message: '提交成功' });
      }, 1000);
    });
    Dialog.confirm({
      title: '提示',
      message: '提交后不可修改，确认提交吗？',
      beforeClose
    });
  },

  checkAnswer() {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})