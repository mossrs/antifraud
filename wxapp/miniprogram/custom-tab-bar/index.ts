Component({
  data: {
    activeTab: undefined as unknown as string
  },

  methods: {
    onTabChange(e: { detail: string }) {
      const { detail } = e;
      this.setData({ activeTab: detail });
      wx.switchTab({ url: `../${detail}/${detail}` });
    }
  }
})