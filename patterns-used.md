# Patterns Used

__Student__: ___Your name and UO here________________________________________

This is just an example, taken from the slides, to be used as a template.
Make sure you update this file to reflect the actual pattern or patterns
used and its correspondence with the specific classes, interfaces and relevant
methods and attributes of your solution.

## Observer

Participants:

- _Subject_: `SecurityNotifier`
  - attach(Observer) -> `addObserver(SecurityObserver)`
  - detach(Observer) -> `removeObserver(SecurityObserver)`
  - notify() -> `updateObservers()`
- _Observer_: `SecurityObserver`
  - update() -> `notify(device: int, event: int)`
- _ConcreteSubject_: `SecurityMonitor`, `SecurityClient`
  - getState() -> `getValue()`
  - setState() -> - (not defined)

## Adapter

Participants:

- _Target_: `SecurityObserver`
  - request() -> `notify(device: int, event: int)`
- _Adaptee_: `SecurityMonitor`
  - specificRequest() -> `showAlarm(event: int, source: Object)`
- _Adapter_: `SecurityAdapter`
