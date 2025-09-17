# Etapa 1: Build da aplicação Angular
FROM node:18-alpine as build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Etapa 2: Servir os arquivos estáticos com nginx
FROM nginx:alpine

# Remove arquivos padrão do nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos buildados
COPY --from=build /app/dist/gestao-eventos-frontend/browser /usr/share/nginx/html

# Copia configuração personalizada do nginx
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
