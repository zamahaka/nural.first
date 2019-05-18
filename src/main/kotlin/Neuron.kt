class Neuron(var w: Double, var q: Double) {
    var x: Double = 0.0
    var y: Double = 0.0
    var t: Double = 0.0

    fun findY() {
        y = x * w
    }

    fun findNextW() {
        w += q * x * (t - y)
    }

}