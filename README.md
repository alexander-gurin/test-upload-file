# test-upload-file

Инструция по запуску:
1) Загрузить исходники.
2) В проекте, в файле application.properties изменить значение сойства app.prop.upload-folder (папка для хранения файла).
3) В папке проекта выполняем mvn package
4) Далее mvn spring-boot:run

Запуск war:
1) Загрузить .war файл: https://drive.google.com/open?id=1TrpRKgpFvDqwOkNgTvJxETh4A1fxFq6R
2) Запустить: java -jar ./test-upload-1.0-SNAPSHOT.war

Сервис должен подняться по адресу: http://localhost:8080/transfer
