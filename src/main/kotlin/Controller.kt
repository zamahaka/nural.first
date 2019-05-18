import javafx.fxml.FXML
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.scene.control.TextArea
import javafx.scene.control.TextField

class Controller {
    @FXML
    private lateinit var w: TextField
    @FXML
    private lateinit var q: TextField
    @FXML
    private lateinit var textArea: TextArea
    @FXML
    private lateinit var chartP: LineChart<Number, Number>

    @FXML
    fun onClickSubmitButton() {
        textArea.clear()
        chartP.data.clear()


        val neuron = Neuron(w.text.toDouble(), q.text.toDouble())
        val seriesF = XYChart.Series<Number, Number>()
        val seriesN = XYChart.Series<Number, Number>()

        seriesF.name = "y = x^2"
        seriesN.name = "Neural network"

        //teach
        doubleRange(from = .0, to = .5, step = .001) { x ->
            val t = x * x
            neuron.x = x
            neuron.t = t
            neuron.findY()

            neuron.findNextW()
        }

        //display neuron
        doubleRange(from = .5, to = 1.0) { x ->
            neuron.apply {
                this.x = x
                findY()
            }

            textArea.appendText("x = ${"%.3f".format(x)} y = ${"%.3f\n".format(neuron.y)}")

            seriesN.data.add(XYChart.Data(x, neuron.y))
        }

        textArea.appendText("w = ${"%.3f\n".format(neuron.w)}")

        //display y = x^2
        doubleRange(from = .0, to = 1.1) { x -> seriesF.data.add(XYChart.Data(x, x * x)) }


        chartP.data.add(seriesF)
        chartP.data.add(seriesN)
    }
}

private fun doubleRange(from: Double, to: Double, step: Double = .1, op: (Double) -> Unit) {
    var x = from
    while (x <= to) {
        op(x)
        x += step
    }
}