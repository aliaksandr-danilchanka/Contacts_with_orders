package danilchanka.aliaksandr.contacts.util

class Utils {

    companion object {

        val CONTACT_PICTURE_URL_PREFIX = "https://inloop-contacts.appspot.com/"

        fun getContactPictureUrl(pictureUrl: String?): String {
            return CONTACT_PICTURE_URL_PREFIX + pictureUrl
        }
    }
}