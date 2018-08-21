function plot_result(fig, B, filename)
    f = figure(fig);

    % Extract column data
    b = B(:, 1);
    t_D = B(:, 2);
    t_Astar = B(:, 3);
    t_JPS = B(:, 4);
    rows = size(B, 1);

    % Plot of the running time
    subplot(1, 3, 1)
%    figure(1)
    plot(b, t_D, "b"); hold on;
    plot(b, t_Astar, "g");
    plot(b, t_JPS, "y");
    %axis square;
    legend("D", "A*", "JPS", "location", "northwest")
    title("Running time")
    xlabel("Bucket")
    ylabel("Average time (ms)")

    % Plot of the speedup factor (compared to Dijkstra)
    subplot(1, 3, 2)
%    figure(2)
    plot(b, t_D ./ t_Astar, "g"); hold on;
    plot(b, t_D ./ t_JPS, "y");
    %axis square;
    legend("A*", "JPS", "location", "northwest")
    title("Speedup over Dijkstra")
    xlabel("Bucket")
    ylabel("Average speedup factor")

    % Plot of the speedup factor (compared to A*)
    subplot(1, 3, 3)
%    figure(3)
    plot(b, t_Astar ./ t_JPS, "y");
    %axis square;
    legend("JPS", "location", "northwest")
    title("Speedup over A*")
    xlabel("Bucket")
    ylabel("Average speedup factor")

    if !isempty(filename)
        saveas(f, strcat(filename, ".png"))
    end
end
