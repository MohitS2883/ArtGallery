package kotlinBasicsUnit3

fun main() {
    val phone = Phone()
    phone.switchOn()
    phone.checkPhoneScreenLight()

    val foldablePhone = FoldablePhone(foldPhoneScreenOn = true)
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()

    foldablePhone.changeFoldState()
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()

    foldablePhone.changeFoldState()
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()

}

open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var foldPhoneScreenOn: Boolean) :
    Phone() {
    var isPhoneFolded: Boolean = true
    override fun switchOn() {
        if (!isPhoneFolded) {
            super.switchOn()
        } else {
            println("Can't turn on the screen as the phone is folded")
        }
    }

    fun changeFoldState() {
        isPhoneFolded = !isPhoneFolded
        println("Is the phone folded:" + isPhoneFolded)
        if (isPhoneFolded) {
            switchOff()
        }
    }

    fun checkFoldState() {
        val foldState = if (isPhoneFolded) "folded" else "unfolded"
        val screenStatus = if (isScreenLightOn) "on" else "off"
        println("The phone is $foldState, and the screen is $screenStatus.")
    }
}