FROM node:23

WORKDIR /app

COPY package*.json ./

RUN npm install -g @angular/cli

RUN npm cache clean --force

RUN npm ci


CMD ["ng", "serve", "--host", "0.0.0.0"]