function plot_result(fig, B, filename, y_limits)
    f = figure(fig);

    % Extract column data
    b = B(:, 1);
    t_D = B(:, 2);
    t_Astar = B(:, 3);
    t_JPS = B(:, 4);
    rows = size(B, 1);

    % RGB color codes
    blue = [0 63.5 100]/100;
    green = [38 84.7 21.2]/100;
    yellow = [97.3 72.9 0]/100;

    % Plot of the running time
    subplot(1, 3, 1)
    plot(b, t_D, "color", blue); hold on;
    plot(b, t_Astar, "color", green);
    plot(b, t_JPS, "color", yellow);
    legend("Dijkstra", "A*", "JPS", "location", "northwest")
    title("Running time")
    xlabel("Bucket")
    ylabel("Average time (ms)")
    if length(y_limits) > 0
        ylim(y_limits(1, 1:2));
    end

    % Plot of the speedup factor (compared to Dijkstra)
    subplot(1, 3, 2)
    plot(b, t_D ./ t_Astar, "color", green); hold on;
    plot(b, t_D ./ t_JPS, "color", yellow);
    legend("A*", "JPS", "location", "northwest")
    title("Speedup over Dijkstra")
    xlabel("Bucket")
    ylabel("Average speedup factor")
    if length(y_limits) > 0
        ylim(y_limits(2, 1:2));
    end

    % Plot of the speedup factor (compared to A*)
    subplot(1, 3, 3)
    plot(b, t_Astar ./ t_JPS, "color", yellow);
    legend("JPS", "location", "northwest")
    title("Speedup over A*")
    xlabel("Bucket")
    ylabel("Average speedup factor")
    if length(y_limits) > 0
        ylim(y_limits(3, 1:2));
    end

    resize_figure(f, 1000, 230);

    if ~isempty(filename)
        print(filename, "-dpng")
    end
end
