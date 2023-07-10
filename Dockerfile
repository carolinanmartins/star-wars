FROM alpine:latest
RUN mkdir /starwars-api
COPY ./target/* /starwars-api
CMD ["sh"]