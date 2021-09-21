package resolvers;

import dto.Report;

/**
 * Интерфейс для контракта разных реализаций.
 */
public interface Resolver {
    Report resolveIt();
}
