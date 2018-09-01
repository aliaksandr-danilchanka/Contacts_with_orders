package danilchanka.aliaksandr.contacts.util

class Utils {

    companion object {

        private const val CONTACT_PICTURE_URL_PREFIX = "https://inloop-contacts.appspot.com/"
        const val BASE_URL = "https://inloop-contacts.appspot.com/_ah/api/"

        fun getContactPictureUrl(pictureUrl: String?): String {
            return CONTACT_PICTURE_URL_PREFIX + pictureUrl
        }
    }
}