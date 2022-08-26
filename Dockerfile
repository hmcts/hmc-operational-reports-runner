ARG APP_INSIGHTS_AGENT_VERSION=3.2.10

# Application image
ARG PLATFORM=""

FROM hmctspublic.azurecr.io/base/java:17-distroless

COPY lib/AI-Agent.xml /opt/app/
COPY build/libs/hmc-operational-reports-runner.jar /opt/app/

EXPOSE 4459
CMD [ "hmc-operational-reports-runner.jar" ]
