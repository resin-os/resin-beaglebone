# disable modules compression
RESIN_CONFIGS_append = " no_modules_compression"
RESIN_CONFIGS[no_modules_compression]=" \
    CONFIG_MODULE_COMPRESS=n \
    CONFIG_MODULE_COMPRESS_XZ=n \
"

KERNEL_DEVICETREE_beaglebone += " am335x-boneblack-uboot.dtb \
"

SRC_URI_append_beaglebone = " \
	file://0001-Use-bbbw-dts-from-4.9.patch \
"

SRC_URI_append_beaglebone-green-wifi = " \
        file://0001-Use-kernel-4.9-BBGW-dts-version.patch \
"

RESIN_CONFIGS_append = " panic_no_reboot"
RESIN_CONFIGS[panic_no_reboot] = "CONFIG_PANIC_TIMEOUT=0"