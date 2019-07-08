# Patrón de diseño

Se utilizará Clean Architecture con MVVM, como definición inicial se está utilizando:

- Retrofit
- Android X
- Gson
- OkHttp
- Lifecycle
- ConstraintLayout

Los proyectos deberán ser creados con **Kotlin** y **Java 8**

> Se recomienda utilizar DataBinding, Android RX, Java RX e inyección de dependencias

# Estructura

### data
- api 
	- ApiInterface: **Interface** que contiene las funciones de las peticiones REST
		> Se recomienda utilizar Retrofit 
	- ApiManager:  **Object** que genera una instancia de ApiInterface.
		Aquí se definen Interceptors, OkHttpClients, Timeouts, Logs, Url base, Converters, Certificate Pinning y/o Mutual authentication
		> HttpLoggingInterceptor sólo debe imprimir Logs en modo Debug (BuildConfig.DEBUG)<br>
		> Utilizar un timeout de 10 segundos<br>
		> ConverterFactory cambiará respecto a lo que se utilice: Retrofit, Rx, Coroutines
- db
	- dao
		- ClassDao: **Interface** que contiene las peticiones a base de datos
			>  Utilizar Room  <br>
			> Utilizar coroutines para delegar a otro hilo las peticiones a base de datos
	- entity
		- ClassEntity: **Data class** que contiene el nombre de la tabla donde se almacenarán localmente los datos
	- movie
		- ClassDataStore: **Interface** que contiene la petición que necesita ser implementada en ClassDbDataStore y ClassServiceDataStore
		- ClassDataStoreFactory: **Class** que retorna la data dependiendo de donde se obtenga, puede ser de base de datos local o la conexión con el servidor
		- ClassDbDataStore: **Class** que retorna la data del obtenida desde base de datos local
	- util:
		- Converters: **Class** utilitaria de Room para formato de fechas
	- AppDataBase: **Abstract class** donde se instancia la base de datos
		> Se recomienda utilizar Room
- entity
	- request
		- ClassRequest: **Data class** que contiene el json necesario para realizar una petición al servidor
	- response
		- ErrorResponse: **Data class** que contiene el error base de las peticiones al servidor
		- ClassResponse: **Data class** que contiene la formato de la respuesta del servidor
	> Se recomienda utilizar Gson y SerializedName, para no tener variables con guiones o guiones bajo
- mapper
	- ErrorMapper: **Object** que permite transformar ErrorResponse a ErrorModel
	- ClassMapper: **Object** que permite transformar a ClassModel la respuesta del servidor y de base de datos local
	> Se recomienda utilizar mappers, porque ayudan a controlar la consistencia de la data y si backend cambia los contratos, sólo se cambiaría en estas clases
- repository
	- ClassDataRepository: **Class** que implementa ClassRepository y retorna los datos obtenidos en ClassDataStoreFactory
- service
	- feature
		- ClassServiceDataStore: **Class** que almacena en base de datos local la data obtenida del servidor, además retorna dicha data
- util
	- ErrorUtil: **Object** que permite transformar la respuesta del servidor a un error genérico que tenemos definida en el proyecto
	- Extensions: **Class** utilitaria quite permite disminuir las líneas de código
	- RetrofitErrorUtil: **Object** que permite transformar la respuesta del servidor a una clase de error que tenemos definida en el proyecto
	- RetrofitException: **Class** que permite manejar los errores de las respuestas del servidor
### domain
- callback
	- BaseCallback: - **Interface** que contiene 3 funciones (onSuccess, OnError y OnFailure) que retornarán las respuestas del servidor o de la base de datos local  
		- OnSuccess: Respuesta satisfactoria 
		- OnError: Retorna un error controlado del servidor  
		- OnFailure: Retorna un error no controlado del servidor, por ejemplo: Timeouts, Servidor caído, etc
- model
	- DefaultErrorModel: **Data class** que contiene un error genérico
		> Se recomienda tener una clase con errores que no pueden ser controlados por el servidor
	- ErrorModel: **Data class** que contiene un error controlado del servidor
		> Se recomienda tener una clase base de errores controlados por el servidor
	- ClassModel: **Data class** que contiene una respuesta del servidor
- repository
	- ClassRepository: **Interface** que necesita ser utilizada en ClassUseCase
- usecase
	- ClassUseCase: **Class** que se utiliza en ClassViewModel y retorna mediante ClassRepository la data obtenida del servidor o de la base de datos local
### presentation
- main
	- ClassActivity: **Activity** que debe inicializar ClassAdapter, ClassDataRepository y ClassViewModel para obtener la data
	- ClassAdapter: **Class Adapter** que permite mostrar la data que retornan como respuesta del servidor o de base de datos local
		> Se recomienda utilizar ‘diffUtil’ para evitar utilizar notifyDataSetChanged(), ya que es más rápido
	- ClassViewModel: **Class** que requiere como parámetro ClassUseCase para obtener data del servidor o de base de datos local.
	Implementa ScopeViewModel.
	Tiene sealed class que va permitir comunicar ClassViewModel con ClassActivity.
		> Se recomienda utilizar ScopeViewModel para tener un mejor manejo de los Scopes<br>
		> Se recomienda utilizar sealed class para comunicarse con la actividad o fragmento
- util
	- Extensions: **Class** utilitaria quite permite disminuir las líneas de código 
		> Se recomienda tener esta clase, para no tener código repetido
	- Scope: **Interface** que se encarga de manejar Jobs de Couroutines
	- ScopeViewModel: **Abstract class** que permite iniciar y destruir Scopes