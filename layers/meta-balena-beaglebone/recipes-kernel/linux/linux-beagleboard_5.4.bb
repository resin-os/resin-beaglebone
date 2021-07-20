SECTION = "kernel"
DESCRIPTION = "Linux kernel for beaglebone boards"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel kernel-resin

require recipes-kernel/linux/setup-defconfig.inc

DEPENDS += "lzop-native"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-boneblack-wireless.dtb am335x-boneblue.dtb am335x-bonegreen.dtb am335x-bonegreen-wireless.dtb am335x-bonegreen-wireless-uboot-univ.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "beaglebone|beagleboard-xm"

S = "${WORKDIR}/git"

TAG = "5.4.70-ti-r21"

PV = "5.4.70+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "b"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_CONFIG_DIR = "${S}/ti_config_fragments"

# SoC specific config fragments are not enabled yet
#KERNEL_CONFIG_FRAGMENTS_append_ti33x = " ${KERNEL_CONFIG_DIR}/am33xx_only.cfg"
#KERNEL_CONFIG_FRAGMENTS_append_ti43x = " ${KERNEL_CONFIG_DIR}/am43xx_only.cfg"

MULTI_CONFIG_BASE_SUFFIX = ""

KERNEL_GIT_URI = "git://github.com/beagleboard/linux.git"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI += " \
    ${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};tag=${TAG};nobranch=1 \
    file://0001-Revert-random-fix-crng_ready-test.patch \
    file://defconfig \
    "

do_install_append() {
    rm -rf ${D}/lib/modules/5.4.70+/modules.builtin.modinfo
}
