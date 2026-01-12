# 🎰 WhoPays
커피, 밥 값 내기를 할 때 흔한 가위바위보 등 보다 간편하면서 긴장감을 주는 이 앱을 이용해 내기 게임을 해보세요!

---
## 📸 Screenshots & Demo



---
## 🛠 Tech Stacks
### UI & UX

- Jetpack Compose: 모든 UI 요소를 선언형 방식으로 구현하여 코드 가독성과 재사용성 극대화.
- Material 3: 최신 디자인 가이드라인 적용 및 다크 모드/라이트 모드 테마 대응.

### Architecture & Pattern

- MVVM (Model-View-ViewModel): UI 로직과 비즈니스 로직의 명확한 분리.
- MVI (Model-View-Intent): 상태(State)를 단일 객체로 관리하고 사용자의 의도(Intent)를 일관된 흐름으로 처리하여 앱의 예측 가능성 극대화.
- Clean Architecture: Domain, Data, Presentation 레이어 구분을 통한 유지보수성 향상.
- UDF (Unidirectional Data Flow): State와 Event의 단방향 흐름을 통한 상태 관리 예측 가능성 확보.

### Libraries & Tools

- Koin (Dependency Injection): 의존성 주입 자동화로 컴포넌트 간 결합도 완화.
- Coroutines & Flow: 비동기 데이터 처리 및 리액티브 스트림 구현.
- Navigation3 Compose: Type-safe한 화면 간 전환.

---
## 🚀 Key Features

1. **\[주요 기능 1: 예) 실시간 데이터 대시보드\]**
   - \[설명: 어떤 기술을 써서 어떻게 구현했는지 간략히 기재\]

---
## 🏗 Architecture

본 프로젝트는 확장성과 테스트 용이성을 위해 Layered Architecture를 채택했습니다.

```text
app
 ├── data          # Data Source, Repository 구현체, DTO
 ├── domain        # UseCase, Repository Interface, Entity (순수 Kotlin)
 ├── presentation  # UI Components, Screens, ViewModels
 └── di            # Koin Modules (Dependency Injection)
```

- Domain Layer: 프레임워크에 의존하지 않는 순수 비즈니스 로직을 포함합니다.
- Data Layer: 네트워크 API와 로컬 DB로부터 데이터를 가져오고 가공합니다.
- UI Layer: Jetpack Compose를 통해 상태(State)를 관찰하고 UI를 렌더링합니다.
