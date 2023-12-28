# Використовуємо офіційний образ для Node.js
FROM node:14 AS frontend

# Задаємо робочий каталог для фронтенду
WORKDIR /frontend

# Копіюємо файли фронтенду
COPY frontend/package.json frontend/package-lock.json ./
COPY frontend/public ./public
COPY frontend/src ./src

# Встановлюємо залежності та збираємо фронтенд
RUN npm install
RUN npm run build

# Використовуємо офіційний образ для Java (OpenJDK)
FROM openjdk:11-jre-slim AS backend

# Задаємо робочий каталог для бекенду
WORKDIR /backend

# Копіюємо файли бекенду
COPY backend/target/*.jar ./

# Задаємо команду для запуску бекенду (може змінюватися залежно від назви вашого JAR файлу)
CMD ["java", "-jar", "your-backend-app.jar"]