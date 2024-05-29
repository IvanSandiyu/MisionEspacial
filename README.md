Misión espacial

Para la realización de la API, se utilizó las siguientes tecnologías:
- Java version 17
- Spring boot version 3.2.5
- JPA
- PostgreSQL
- Para pruebas de endpoints se utilizó Postman

Para la correcta funcionalización de la API, se deberá ingresar las credenciales desde el endpoint “/authenticate” con el siguiente cuerpo:
![autenticacion](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/authenticate.png)

En la cual si todo es correcto, se devolverá en pantalla “Autenticación exitosa”. De lo contrario, la pantalla mostrará “Autenticación fallida”
![autenticacionexitosa](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/auexitosa.png)

Luego de autenticarse exitosamente, procederemos al endpoint “/instructions”, en el cual le enviaremos algunas de estas tres opciones:
![scan](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/scan.png)


![deply](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/deply.png)


![collect](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/collect.png)


Scan: Si todo está correcto, se le mostrará lo siguiente:


![resulScan](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/resultadoScan.png)


Deploy Rover: Si está todo correcto, se le mostrará lo siguiente:


![resulRover](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/resultadoRover.png)



Collect Sample: Si está todo correcto, se le mostrará lo siguiente:


![resulCollect](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/resultsample.png)



Luego de cada acción, podremos ver las telemetrías a través del endpoint “/telemetry”. Este mostrará la última telemetría de la acción ejecutada. Lo que podremos recibir después de cada telemetría es:

Scan: 
![telscan](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/telscan.png)

Deploy Rover:
![telrover](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/telrover.png)


Collect Sample:
![telsample](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/telsample.png)

Base de datos utilizada es Postgresql. Está compuesta por 5 entidades:
- Instruction
- Telemetry
- Scan
- DeployRover
- CollectSample

Entidad Instruction: se encarga de guardar todas las instrucciones procesadas.
Entidad Telemetry: se encarga de guardar todas las telemetrías procesadas
Entidad Scan: se encarga de guardar todos los escaneos que hubo
Entidad DeployRover: se encarga de guardar todos los DeployRover que hubo
Entidad CollectSample: se encarga de guardar todos los CollectSample que hubo

Todas las entidades están conectadas mediante PK Y FK, siendo la PK principal la id de instrucción.

Entidad Instruction: el id se genera automáticamente, nos mostrará la fecha, que acción se ordenó, si la acción se completo o hubo algún error y el resultado
![inst](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/binst.png)



Entidad Telemetry: nos mostrará el id ( el mismo que el de la instrucción ), la instrucción, el resultado y el estado
![tel](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/btel.png)


Entidad Scan: mostrará el id_scan ( el mismo de la instrucción ) y todos los datos de escaneo
![scan](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/bScan.png)


Entidad Deploy Rover: mostrará el id_rover ( el mismo de la instrucción ) y todos los datos del rover
![rover](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/bdeploy.png)


Entidad Collect Sample: mostrará el id_collect ( el mismo de la instrucción ) y todos los datos de lo recolectado
![sample](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/bcollect.png)

Aquí un DER de como se pensó el modelaje de las entidades
![der](https://github.com/IvanSandiyu/MisionEspacial/blob/main/img/der.png)
