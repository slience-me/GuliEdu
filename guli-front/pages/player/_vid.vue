<template>
  <div>
    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/apsara-media-box/imp-web-player/2.16.3/skins/default/aliplayer-min.css" />
    <!-- 阿里云视频播放器脚本 -->
    <script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/apsara-media-box/imp-web-player/2.16.3/aliplayer-min.js"></script>
    <!-- 定义播放器dom -->
    <div id="J_prismPlayer" class="prism-player"/>
  </div>
</template>
<script>
import vod from '@/api/vod'
import courseApi from "~/api/course";

export default {
  layout: 'video',//应用video布局
  data() {
    return {
      vid: '',
      playAuth: ''
    }
  },
  mounted() { //页面渲染之后  created
    this.vid = this.$route.params.vid
    if (this.vid) {//判断路径是否有token值
      this.getVideoData(this.vid)
    }
  },
  methods: {
    getVideoData(vid) {
      console.log("=========getVideoData getPlayAuth===========")
      vod.getPlayAuth(vid)
        .then(response => {
          this.playAuth = response.data.data.playAuth;
          this.vid = vid;
          this.playVideo()
        })
    },
    playVideo() {
      new Aliplayer({
        id: 'J_prismPlayer',
        vid: this.vid, // 视频id
        playauth: this.playAuth, // 播放凭证
        encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
        width: '100%',
        height: '500px',
        // 以下可选设置
        cover: 'https://slienceme.oss-cn-beijing.aliyuncs.com/2024/01/30/e8c577a3148546a89e8545b0940c56ee1525939573202.jpg', // 封面
        qualitySort: 'asc', // 清晰度排序
        mediaType: 'video', // 返回音频还是视频
        autoplay: false, // 自动播放
        isLive: false, // 直播
        rePlay: false, // 循环播放
        preload: true,
        controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
        useH5Prism: true, // 播放器类型：html5
      }, function (player) {
        console.log('播放器创建成功')
      })
    }
  }

}
</script>
