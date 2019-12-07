
import java.awt.Color
import java.awt.Graphics
import java.util.*
import javax.swing.JFrame


class TreeType(private val name: String, color: Color, otherTreeData: String) {
    private val color: Color
    private val otherTreeData: String
    fun draw(g: Graphics, x: Int, y: Int) {
        g.setColor(Color.BLACK)
        g.fillRect(x - 1, y, 3, 5)
        g.setColor(color)
        g.fillOval(x - 5, y - 10, 10, 10)
    }

    init {
        this.color = color
        this.otherTreeData = otherTreeData
    }
}

class Tree(private val x: Int, private val y: Int, type: TreeType) {
    private val type: TreeType
    fun draw(g: Graphics?) {
        if (g != null) {
            type.draw(g, x, y)
        }
    }

    init {
        this.type = type
    }
}

object TreeFactory {
    var treeTypes: MutableMap<String, TreeType?> = mutableMapOf()
    fun getTreeType(name: String, color: Color?, otherTreeData: String?): TreeType? {
        var result = treeTypes[name]
        if (result == null) {
            result = color?.let { TreeType(name, it, otherTreeData!!) }
            treeTypes[name] = result
        }
        return result
    }
}

class Forest: JFrame() {
    private var trees  = mutableListOf<Tree>()

    fun plantTree(x: Int, y: Int, name: String, color: Color, otherTreeData: String) {
        var type = TreeFactory.getTreeType(name, color, otherTreeData)
        var tree = type?.let { Tree(x, y, it) }
        if (tree != null) {
            trees.add(tree)
        };
    }

    @Override
    public override fun paint(graphics: Graphics) {
        for (tree in trees) {
            tree.draw(graphics)
        }
    }
}

object Demo1 {
    var CANVAS_SIZE = 500
    var TREES_TO_DRAW = 1000000
    var TREE_TYPES = 2
    @JvmStatic
    fun main(args: Array<String>) {
        val forest = Forest()
        var i = 0
        while (i < Math.floor(TREES_TO_DRAW / TREE_TYPES.toDouble())) {
            forest.plantTree(
                random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                "Summer Oak", Color.GREEN, "Oak texture stub"
            )
            forest.plantTree(
                random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub"
            )
            i++
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE)
        forest.setVisible(true)
        println(TREES_TO_DRAW.toString() + " trees drawn")
        println("---------------------")
        println("Memory usage:")
        println("Tree size (8 bytes) * " + TREES_TO_DRAW)
        println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "")
        println("---------------------")
        println(
            "Total: " + (TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024 +
                    "MB (instead of " + TREES_TO_DRAW * 38 / 1024 / 1024 + "MB)"
        )
    }

    private fun random(min: Int, max: Int): Int {
        return min + (Math.random() * (max - min + 1)).toInt()
    }
}