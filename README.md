# test-upload-file

Инструция по запуску:
1) Загрузить исходники.
2) В проекте, в файле application.properties изменить значение сойства app.prop.upload-folder (папка для хранения файла).
3) В папке проекта выполняем mvn package
4) Далее mvn spring-boot:run

Сервис должен подняться по адресу: http://localhost:8080/upload
