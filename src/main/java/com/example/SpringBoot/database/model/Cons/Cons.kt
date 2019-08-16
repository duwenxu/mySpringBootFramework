

object Cons {
    const val WEBSOCKET_NOTIFICATIONS = "/queue/notifications"
    const val APP_WEBSOCKET_NOTIFICATIONS = "/queue/notificationsAPP"
    const val WEBSOCKET_HISTORY = "/queue/history"
    const val WEBSOCKET_EVENT = "/queue/eventMessage"
    const val WEBSOCKET_LINK = "/queue/link"
    const val WEBSOCKET_ALL_LINK = "/queue/allLink"
    const val WEBSOCKET_SCHEDULER_EQUIPMENT = "/queue/scheduler/equipment"
    const val WEBSOCKET_TIMEJUMP = "/queue/timeJump"
    const val WEBSOCKET_YC_PARAMETERS = "/queue/ycParam"    //遥测参数名称
    const val WEBSOCKET_COLLIDE_RESULT = "/query/collide"   //碰撞预警文件

    const val TIME_MIN = (60 * 1000).toLong()
    const val TIME_DAY = 24 * 60 * TIME_MIN


    const val NOT_HIDDLE = 0
    const val HIDDLE = 1

    /**
     * 终端类型识别
     * 1 PC 4 APP
     */
    const val TERMINAL_PC = 1
    const val TERMINAL_MT = 2
    const val TERMINAL_LED = 3
    const val TERMINAL_APP = 4

    /**
     * app 验权header
     */
    val HEADER_NAME = "Authorization"

    /**
     * 页面属性
     */
    val YC_PAGE = 1
    val MESSAGE_PAGE = 2
    val WC_PAGE = 3
    val TASK_SCHEDULE_PAGE = 4

    /**
     * 显示分类类型
     */
    const val DISPLAY_SCHEME_TYPE_COMMON = 0
    const val DISPLAY_SCHEME_TYPE_USER = 1


    /**
     * 任务计划类型
     */
    const val CENTRAL_SCHEDULE = 1
    const val EQUIPMENT_SCHEDULE = 2
    const val PROGCTRL_SCHEDULE = 3
    const val TELECTRL_SCHEDULE = 4

    /**
     * GNC时  星上时   星地误差
     */
    const val GNC_TIME = 20
    const val TUBE_TIME = 21
    /**
     * 星地时差CODE
     */
    const val STAR_LOCAL_CODE = "m165"

    /**
     * GNC时 星上时 星地误差标识
     */
    const val GNC_TIME_SIGN = "GNC_TIME_SIGN"
    const val TUBE_TIME_SIGN = "TUBE_TIME_SIGN"
    const val STAR_LOCAL_SIGN = "STAR_LOCAL_SIGN"


    /**
     * 数据库常量
     */
    const val ID_LENGTH = 32
    const val FILE_NAME_LENGTH = 256
    const val NAME_LENGTH = 256
    const val FILE_EXT_LENGTH = 20
    const val FILE_LENGTH = 20
    const val FILE_STORAGE_LENGTH = 1024
    const val BIG_STRING=65535
    const val SMALL_INT = 4

    /**
     * 创建人 0 代表系统
     */
    const val SYSTEM = 0


    /**
     * orbit 数据属性分类
     */
    const val XPOD_PODE = 2
    const val XPOD_RES = 3
    const val ORBIT_THREE = 6
    const val OC_A = 7
    const val OC_D = 8
    const val OC_E = 9
    const val OC_R = 10

    /**
     * 对大列表weboskcet 分页发送
     */
    const val DEFAULT_SIZE = 1000

    /**
     * websocket 消息类型
     */
    const val SPLIT_DATA = "split_data"


    /**
     * 缓存对应的类型
     */
    const val OFFSET_TIME = 1  //跳时
    const val DEVICE_INFO = 2   //设备信息
    const val SPACE_DEVICE = 3   //任务下设备  航天器设备就可对应
    const val TELEMETER_PARAM = 4    //遥测参数结构
    const val TASK_BEGIN = 5 //任务开始时间
    const val NODE_INFO = 6 //节点信息
    const val YC_PACKAGE = 7 //遥测包号，识别字对应关系
    const val TWO_LINE = 8 //两行根数

    /**
     * 云豹系统日志最大数量
     */
    const val YB_OPT_LOGS_NUM = 500L
    const val YK_SEND_NUM = 20L


    /**
     * 默认分页
     */
    const val DEFAULT_PAGE = 20


    /**
     * 文件类数据下载的FTP地址
     */
    const val FTP_IPARR = "172.23.0.21"
    const val FTP_PORT = 21
    const val FTP_USERNAME = "ftpadmin"
    const val FTP_PASSWORD = "123456"

    /**
     * 云豹外测数据发送的帧频
     */
    const val HEARTBEAT_RATE: Long = 1000
    const val EXTERNAL_RATE: Long = 1000
    const val YCPARAM_RATE: Long = 1000
    const val YCORIPARAM_RATE: Long = 1000

    /**
     * 页面订阅类型
     */
    const val ADD_TYPE = 1    //新增订阅页面
    const val RED_TYPE = 0    //关闭订阅页面
    const val CHANGE_TYPE = 2     //设备切换订阅


    /**
     * 页面自定义id
     */
    const val COLLIDEISION_PAGE = -1
    const val CUSTOM_PAGE = -2


    /**
     * hdfs常量
     */
    const val HDFS_PORT = 9000

    /**
     * 暂时时间常量
     */
    const val DEAD_TIME="20191001000000"

    /**
     * 加密狗授权码
     */
    const val VENDOR_CODE="wEG6h5XDB0hlT6IaAf3e3fDwgfSDkVfiAZdSt2nj/vD/nQYt38OzbaylYRq2owHsO8UVkmjfaQHQM9IfLBsOV/wAxU1tmX/jCUmDY2vfJ9Sv2Deml6097aoHfrMzrIl3n+v5GONVEbu0IIz3VMh83b8DF5SOUptAxrD2F7zwI9huhSRHHVAoc6ha1J92gMSbj/f1QJCcnK8Wrdzn+NEg0e+fWCu4sow8C2YZQo5oLm3ardshZfxXDlqSYg/PUoefvm9WZhqG8mjO9bmePWDYfhoYZp5M0AnE2AwZEJlbo4w3vS7PZTDuNJOx8rxkOSa6qKuHAMiQEjIY978Y1Vbloz//EUq0nIOVqueWmAtgPzjgiVn9sgeB8EoEomkPRkqX7mZt7O3ekCK3w9AIhTuT0k5VTAU9/HNmTubhrLlzeuzi/hyxsP6YEAfryWIBBycsay1u1QX7ZPCxhNIFbxdFI0dAztPEmxUNausjnRNN94NAmzTS26Yv/XKUR+gtW0Rh0Dh5pa06tnpsVzJfAo71IT8X01rVgq033Ponw7ueJogy/hRAfe6eHtzhEU8Xf+PqgQRplASKLZKe9djIQf/aEsC9L8RZ9clgZamGBqe8QbE48Yh04GuNGdToA1wFxDfmhiK7aM4bhVg/1MRMOsaObEshY7YHDLNfF0GLMHEL1Axu1ItT/e7n89Zr860SCWeWLmkfuZH+eRbTuDI6+v1J2yYWlLIngjrA5sbT0YgdWpekT8csPeGw70eC5nEjorT/xb26kmqQDmfLf6YWMDxTE5JjrY0v22JFYj2CxOIC9NZ5ZnYAjTNeSsWNTChfw2J0tUeXKaeOLRaCS8Lb8yrPBxSG3dxvVev2QEytP5o6hi3mJ+POvmXwC+ChrvofxccjwL9izkCxWxnj3auvy+tE+rMgPIJQEiXZlU8svyjBSG47z3y78Jj3oxo27uZ3CxDsyhnNNjiX86lGkXePqDkA5g=="

    /**
     * 默认密码
     */
    const val defaultPassword="123456"
}