package GUISamples

import java.util.Arrays
import javafx.application.Application
import javafx.application.Application.launch
import javafx.collections.FXCollections
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.chart.CategoryAxis
import javafx.stage.Stage
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.StackedAreaChart
import javafx.scene.chart.XYChart
import javafx.scene.control.ComboBox
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.chart.PieChart
import javafx.scene.chart.LineChart
import java.util.Collections.addAll
import javafx.scene.chart.StackedBarChart

class StackedAreaChartExample : Application() {
    var graph: Node? = null

    override fun start(stage: Stage) {
        val langs = FXCollections.observableArrayList("Line", "Chart", "Circle", "Hist")
        val langsComboBox = ComboBox(langs) // создаем combo box
        langsComboBox.setValue("Line") // устанавливаем выбранный элемент по умолчанию

        graph = addLine() //дефолтный граф

        //Создаем рут объект
        val root = VBox(langsComboBox)
        root.children.add(graph)

        // получаем выбранный элемент
        langsComboBox.setOnAction {
            when (langsComboBox.value) {
                "Chart" -> {
                    root.children.remove(graph)
                    graph = addChart()
                    root.children.add(graph)
                }
                "Circle" -> {
                    root.children.remove(graph)
                    graph = addCircle()
                    root.children.add(graph)
                }
                "Hist" -> {
                    root.children.remove(graph)
                    graph = addHist()
                    root.children.add(graph)
                }
                "Line" -> {
                    root.children.remove(graph)
                    graph = addLine()
                    root.children.add(graph)
                }
                else -> {
                    root.children.remove(graph)
                    graph = addLine()
                    root.children.add(graph)
                }
            }
        }

        //Создаём сцену
        val scene = Scene(root, 600.0, 400.0)
        stage.title = "Stacked Area Chart"
        //Устанавливаем сцену
        stage.scene = scene
        //Показываем окно
        stage.show()
    }

    fun addChart(): StackedAreaChart<String, Number> {

        //Defining the axes
        val xAxis = CategoryAxis()
        xAxis.categories = FXCollections.observableArrayList(
            Arrays.asList("1750", "1800", "1850", "1900", "1950", "1999", "2050")
        )

        val yAxis = NumberAxis(0.0, 10000.0, 2500.0)
        yAxis.label = "Population in Millions"

        //Creating the Area chart
        val areaChart = StackedAreaChart(xAxis, yAxis)
        areaChart.title = "Historic and Estimated Worldwide Population Growth by Region"

        //Prepare XYChart.Series objects by setting data
        val series1 = XYChart.Series<String, Number>()

        series1.setName("Asia")
        series1.getData().add(XYChart.Data("1750", 502))
        series1.getData().add(XYChart.Data("1800", 635))
        series1.getData().add(XYChart.Data("1850", 809))
        series1.getData().add(XYChart.Data("1900", 947))
        series1.getData().add(XYChart.Data("1950", 1402))
        series1.getData().add(XYChart.Data("1999", 3634))
        series1.getData().add(XYChart.Data("2050", 5268))

        val series2 = XYChart.Series<String, Number>()
        series2.setName("Africa")
        series2.getData().add(XYChart.Data("1750", 106))
        series2.getData().add(XYChart.Data("1800", 107))
        series2.getData().add(XYChart.Data("1850", 111))
        series2.getData().add(XYChart.Data("1900", 133))
        series2.getData().add(XYChart.Data("1950", 221))
        series2.getData().add(XYChart.Data("1999", 767))
        series2.getData().add(XYChart.Data("2050", 1766))

        val series3 = XYChart.Series<String, Number>()
        series3.setName("Europe")

        series3.getData().add(XYChart.Data("1750", 163))
        series3.getData().add(XYChart.Data("1800", 203))
        series3.getData().add(XYChart.Data("1850", 276))
        series3.getData().add(XYChart.Data("1900", 408))
        series3.getData().add(XYChart.Data("1950", 547))
        series3.getData().add(XYChart.Data("1999", 729))
        series3.getData().add(XYChart.Data("2050", 628))

        val series4 = XYChart.Series<String, Number>()
        series4.setName("America")
        series4.getData().add(XYChart.Data("1750", 18))
        series4.getData().add(XYChart.Data("1800", 31))
        series4.getData().add(XYChart.Data("1850", 54))
        series4.getData().add(XYChart.Data("1900", 156))
        series4.getData().add(XYChart.Data("1950", 339))
        series4.getData().add(XYChart.Data("1999", 818))
        series4.getData().add(XYChart.Data("2050", 1201))

        val series5 = XYChart.Series<String, Number>()
        series5.setName("Oceania")
        series5.getData().add(XYChart.Data("1750", 2))
        series5.getData().add(XYChart.Data("1800", 2))
        series5.getData().add(XYChart.Data("1850", 2))
        series5.getData().add(XYChart.Data("1900", 6))
        series5.getData().add(XYChart.Data("1950", 13))
        series5.getData().add(XYChart.Data("1999", 30))
        series5.getData().add(XYChart.Data("2050", 46))

        //Setting the data to area chart
        areaChart.data.addAll(series1, series2, series3, series4, series5)

        return areaChart
    }

    fun addCircle(): PieChart {
        //Preparing ObservbleList object
        val pieChartData = FXCollections.observableArrayList(
            PieChart.Data("Iphone 5S", 13.0),
            PieChart.Data("Samsung Grand", 25.0),
            PieChart.Data("MOTO G", 10.0),
            PieChart.Data("Nokia Lumia", 22.0)
        )

        //Creating a Pie chart
        val pieChart = PieChart(pieChartData)

        //Setting the title of the Pie chart
        pieChart.title = "Mobile Sales"

        //setting the direction to arrange the data
        pieChart.isClockwise = true

        //Setting the length of the label line
        pieChart.labelLineLength = 50.0

        //Setting the labels of the pie chart visible
        pieChart.labelsVisible = true

        //Setting the start angle of the pie chart
        pieChart.startAngle = 180.0
        return pieChart
    }

    fun addLine(): LineChart<Number, Number> {
        //Defining the x axis
        val xAxis = NumberAxis(1960.0, 2020.0, 10.0)
        xAxis.label = "Years"

        //Defining the y axis
        val yAxis = NumberAxis(0.0, 350.0, 50.0)
        yAxis.label = "No.of schools"

        //Creating the line chart
        val linechart = LineChart(xAxis, yAxis)

        //Prepare XYChart.Series objects by setting data
        val series = XYChart.Series<Number, Number>()
        series.setName("No of schools in an year")

        series.getData().add(XYChart.Data(1970, 15))
        series.getData().add(XYChart.Data(1980, 30))
        series.getData().add(XYChart.Data(1990, 60))
        series.getData().add(XYChart.Data(2000, 120))
        series.getData().add(XYChart.Data(2013, 240))
        series.getData().add(XYChart.Data(2014, 300))

        //Setting the data to Line chart
        linechart.data.add(series)
        return linechart
    }

    fun addHist(): StackedBarChart<String, Number> {
        //Defining the axes
        val xAxis = CategoryAxis()
        xAxis.categories =
            FXCollections.observableArrayList(Arrays.asList("Africa", "America", "Asia", "Europe", "Oceania"))

        xAxis.label = "category"
        val yAxis = NumberAxis()
        yAxis.label = "Population (In millions)"

        //Creating the Bar chart
        val stackedBarChart = StackedBarChart(xAxis, yAxis)
        stackedBarChart.title = "Historic World Population by Region"

        //Prepare XYChart.Series objects by setting data
        val series1 = XYChart.Series<String, Number>()
        series1.name = "1800"
        series1.data.add(XYChart.Data("Africa", 107))
        series1.data.add(XYChart.Data("America", 31))
        series1.data.add(XYChart.Data("Asia", 635))
        series1.data.add(XYChart.Data("Europe", 203))
        series1.data.add(XYChart.Data("Oceania", 2))

        val series2 = XYChart.Series<String, Number>()
        series2.name = "1900"
        series2.data.add(XYChart.Data("Africa", 133))
        series2.data.add(XYChart.Data("America", 156))
        series2.data.add(XYChart.Data("Asia", 947))
        series2.data.add(XYChart.Data("Europe", 408))
        series1.data.add(XYChart.Data("Oceania", 6))

        val series3 = XYChart.Series<String, Number>()
        series3.name = "2008"
        series3.data.add(XYChart.Data("Africa", 973))
        series3.data.add(XYChart.Data("America", 914))
        series3.data.add(XYChart.Data("Asia", 4054))
        series3.data.add(XYChart.Data("Europe", 732))
        series1.data.add(XYChart.Data("Oceania", 34))

        //Setting the data to bar chart
        stackedBarChart.data.addAll(series1, series2, series3)

        return stackedBarChart
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(StackedAreaChartExample::class.java)
        }
    }
}