package com.zihany.weather.location


/**
 * 定位工具接口
 */
interface LocationUtils {
    /**
     * 开始定位
     */
    fun start()

    /**
     * 停止定位
     */
    fun stop()

    /**
     * 销毁
     */
    fun destory()
}