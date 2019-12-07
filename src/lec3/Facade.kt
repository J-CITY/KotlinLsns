//сложный фреймворк
class AudioFile(file: String) {
    fun save(){}
}

class Mp3Codec {

}
class FlacCodec {

}

class CodecFactory {
    public fun extract(file: AudioFile){}
}

class BitrateReader {
    fun read(filename: String, sourceCodec: Unit): BitrateReader {
        return BitrateReader()
    }

    fun convert(buffer: BitrateReader, destinationCodec: Any): String {
        return ""
    }

}

class AudioMixer {
    fun fix(result: String): String {
        return ""
    }

}

//конец интерфейса
///////////
// Вместо этого мы создаём Фасад — простой интерфейс для работы
// со сложным фреймворком. Фасад не имеет всей функциональности
// фреймворка, но зато скрывает его сложность от клиентов.
class AudioConverter {
    public fun convert(filename: String, format: String): AudioFile {
        var file = AudioFile(filename)
        var sourceCodec = CodecFactory().extract(file)
        var destinationCodec = if (format == "mp3")
            Mp3Codec()
        else
            FlacCodec()
        var buffer = BitrateReader().read(filename, sourceCodec)
        var result = BitrateReader().convert(buffer, destinationCodec)
        result = (AudioMixer()).fix(result)
        return AudioFile(result)
    }
}
// Приложение не зависит от сложного фреймворка конвертации
// видео. Кстати, если вы вдруг решите сменить фреймворк, вам
// нужно будет переписать только класс фасада.
fun main() {
    var convertor = AudioConverter()
    var mp3 = convertor.convert("in.flac", "mp3")
    mp3.save()
}
