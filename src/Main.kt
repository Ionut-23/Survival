import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

var cubosActuales = 0
var lenaActual = 0
var ramasActuales = 0
var comidaActual = 0

const val CUBOS_NECESARIOS = 4
const val LENA_NECESARIA = 2
const val RAMAS_NECESARIAS = 1
const val COMIDA_NECESARIA = 1

val mutex = Mutex()

fun main() {

    comenzar()
    Thread.sleep(80000)  //IMPORTANTE: TE TIENES QUE ESPERAR 80 SEGUNDOS PARA QUE TE SALGA LO DE "BARCA CONSTRUIDA...."!!!

    if (cubosActuales == CUBOS_NECESARIOS && lenaActual == LENA_NECESARIA && ramasActuales == RAMAS_NECESARIAS && comidaActual == COMIDA_NECESARIA) {
        println("Barca construida y aprovisionada con éxito")
    }else {
        println("No salimos...")
    }
}

fun comenzar() {

    GlobalScope.launch {
        runBlocking {
            amigoA()
            amigoB()
            amigoC()
        }
    }
}

suspend fun amigoA(){

    GlobalScope.async {
        repeat(4) {
            println("El amigo Amigo A va a por un cubo de agua")
            delay(4000)
            println("El amigo Amigo A vuelve con un cubo de agua")
            hamaca("Amigo A",1000)
            cubosActuales += 1
        }
    }
}

suspend fun amigoB(){

    GlobalScope.async {
        repeat(2) {
            println("El amigo Amigo B va a por lena")
            hacha("Amigo B", 5000)
            lenaActual += 1
            hamaca("Amigo B", 3000)
        }
    }
}

suspend fun amigoC(){

    GlobalScope.async{
        delay(1000)
        println("El amigo Amigo C va a por ramas")
        println("El amigo Amigo C vuelve con ramas")
        delay(3000)
        ramasActuales += 1
        println("El amigo Amigo C va a Cazar")
        hacha("Amigo C",4000)
        comidaActual += 1
    }
}

suspend fun hamaca(nombre : String, tiempo : Long){

    mutex.withLock {
        println("El amigo $nombre, quiere descansar")
        println("El amigo $nombre, se tumba en la hamaca")
        delay(tiempo)
        println("El amigo $nombre, se levanta de la hamaca")
        println("El amigo $nombre, deja de descansar")
    }
}

suspend fun hacha(nombre : String, tiempo:Long){

    mutex.withLock {
        println("El amigo $nombre coge el hacha")
        delay(tiempo)
        println("El amigo $nombre deja el hacha")
        println("El amigo $nombre vuelve con la lena")
    }
}
