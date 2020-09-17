package net.dambakk.ekkokammer.common


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
