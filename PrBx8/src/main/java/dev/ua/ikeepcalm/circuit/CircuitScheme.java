package dev.ua.ikeepcalm.circuit;

public enum CircuitScheme {

    LRC_SCHEME {
        @Override
        public String toString() {
            return """
            1)    ┌─────┐      ┌─┐ ┌─┐ ┌─┐ ┌─┐
            o───┬─┼┼┼┼┼┼┼──────┘ └─┘ └─┘ └─┘ └─┬──o
                │ └─────┘             L        │ 
                │    R                         │
                │                              │
                │                C             │
                │                              │
                │               │ │            │
                └───────────────┤ ├────────────┘
                                │ │
                """;
        }
    },

    RLC_SCHEME{
        @Override
        public String toString() {
            return """
                    2)  ┌─────┐            │ │
             o───┬─┼┼┼┼┼┼┼────────────┤ ├───────┬──o
                    │ └─────┘            │ │       │
                    │    R                         │
                    │                     C        │
                    │                              │
                    │                 L            │
                    │           ┌─┐ ┌─┐ ┌─┐ ┌─┐    │
                    └───────────┘ └─┘ └─┘ └─┘ └────┘
                """;
        }
    },

    CRRL_SCHEME {
        @Override
        public String toString() {
            return """
                3)          ┌─────┐          │ │
                    o───┬───┼┼┼┼┼┼┼──────────┤ ├───────┐
                        │   └─────┘          │ │       │
                      ┌─┼─┐    R1                      │
                      │┼┼┼│                   C        │
                      │┼┼┼│R2                          │
                      └─┼─┘               L            │
                        │           ┌─┐ ┌─┐ ┌─┐ ┌─┐    │
                    o───┴───────────┘ └─┘ └─┘ └─┘ └────┘
                """;
        }
    },


    RRCL_SCHEME {
        @Override
        public String toString() {
            return """
                         4)     ┌─────┐
                o───┬───┼┼┼┼┼┼┼────────────────────┐
                    │   └─────┘                    │
                  ┌─┼─┐    R1                      │
                  │┼┼┼│R2                          │
                  │┼┼┼│                            │
                  └─┼─┘   C           L            │
                    │    │ │    ┌─┐ ┌─┐ ┌─┐ ┌─┐    │
                o───┴────┤ ├────┘ └─┘ └─┘ └─┘ └────┘
                                 │ │
                """;
        }
    };

    public static CircuitScheme getSchemeByOption(String option){
        return switch (option) {
            case "1" -> LRC_SCHEME;
            case "2" -> RLC_SCHEME;
            case "3" -> CRRL_SCHEME;
            case "4" -> RRCL_SCHEME;
            default -> throw new IllegalArgumentException("No such option!");
        };
    }
}
