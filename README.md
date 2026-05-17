# Disruptor Patterns

A Java showcase of [LMAX Disruptor](https://github.com/LMAX-Exchange/disruptor) patterns using a financial quote domain (`Quote`, `Instrument`).

## Running

```bash
./gradlew fatJar
java -jar app/build/libs/app-all.jar <mode>
```

## Patterns

| Mode | Description |
| ---- | ----------- |
| `spsc` | **Single Producer Single Consumer** — one producer, one consumer (`QuotePublisher`). Uses `ProducerType.SINGLE` for lock-free sequence claiming. |
| `multicast` | **Multicast** — one producer, multiple consumers (`QuotePublisher`, `QuoteJournaler`) each receiving every event in parallel. |
| `pipeline` | **Pipeline** — events flow through a chain: `QuoteEnricher` → `QuotePublisher`. Each stage completes before the next begins. |
| `mpsc` | **Multi Producer Single Consumer** — three concurrent producer threads publish to a single `QuotePublisher` consumer. Uses `ProducerType.MULTI`. |
| `loadbalancer` | **Load Balancer** — one producer, three `QuoteWorkerPublisher` consumers. Each worker processes only its partition of events (`sequence % workerCount == workerId`). |
| `batching` | **Batching** — `QuoteBatchHandler` accumulates events and flushes the batch when `endOfBatch` is signalled by the ring buffer. |
| `diamond` | **Diamond** — fan-out to `QuoteLifetimeEnricher` and `QuoteValidityEnricher` running concurrently, fan-in to `QuotePublisher` once both complete. |

## Project Structure

```text
handlers/               EventHandler implementations
patterns/
  spsc/                 SingleProducer
  multicast/            Multicast
  pipeline/             Pipeline
  mpsc/                 MultiProducer
  loadbalancer/         LoadBalancer
  batching/             Batching
  diamond/              Diamond
DisruptorFactory        Builds Disruptor instances (ProducerType + YieldingWaitStrategy)
App                     Orchestrator — selects pattern via CLI argument
```
