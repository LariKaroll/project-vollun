FROM node:20

WORKDIR /app

RUN npm install -g @angular/cli

CMD ["sh", "-c", "ng new frontend --defaults --skip-git && ls"]